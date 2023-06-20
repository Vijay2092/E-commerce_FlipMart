package com.example.FlipMart.Service.impl;

import com.example.FlipMart.Dto.RequestDto.SellerRequestDto;
import com.example.FlipMart.Dto.ResponseDto.SellerResponseDto;
import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.Model.Item;
import com.example.FlipMart.Model.Product;
import com.example.FlipMart.Model.Seller;
import com.example.FlipMart.Repository.SellerRepository;
import com.example.FlipMart.Service.SellerService;
import com.example.FlipMart.exception.SellerNotFoundException;
import com.example.FlipMart.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        // dto -> entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        // entity -> response dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);


    }

    @Override
    public SellerResponseDto updateInfoByEmail(String emailId, String name) throws SellerNotFoundException {

        Seller seller = sellerRepository.findByEmailId(emailId);
        if(seller == null){
            throw new SellerNotFoundException("Seller does not exist!!");
        }
        seller.setName(name);
        Seller savedSeller = sellerRepository.save(seller);
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }

    @Override
    public List<SellerResponseDto> sellerSellProductOfCategory(Category category) {

        List<Seller> sellers = sellerRepository.findAll();

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();

        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            for(Product product : products){
                if(product.getCategory() == category){
                    sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
                    break;
                }
            }
        }
        return sellerResponseDtoList;
    }

    @Override
    public HashMap<String, Integer> allProductsSoldByASeller(String emailId) throws SellerNotFoundException {

        Seller seller = sellerRepository.findByEmailId(emailId);
        if(seller == null){
            throw new SellerNotFoundException("Seller does not exist!!!");
        }

        List<Product> products = seller.getProducts();
        HashMap<String, Integer> productSold = new HashMap<>();

        for(Product product : products){
            productSold.put(product.getName(), 0);
            List<Item> items = product.getItems();
            for(Item item : items){
                if(item.getOrderEntity() != null){
                    productSold.put(product.getName(), productSold.get(product.getName()) + item.getRequiredQuantity());
                }
            }
        }

        return productSold;
    }

    @Override
    public SellerResponseDto sellerWithHighestNoOfProducts() {

        int NoOfProducts = 0;
        Seller ans = null;
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            if(seller.getProducts().size() > NoOfProducts){
                NoOfProducts = seller.getProducts().size();
                ans = seller;
            }
        }

        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(ans);
        return sellerResponseDto;
    }

    @Override
    public SellerResponseDto sellerWithMinimumNoOfProducts() {

        int NoOfProducts = Integer.MAX_VALUE;
        Seller ans = null;
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            if(seller.getProducts().size() < NoOfProducts){
                NoOfProducts = seller.getProducts().size();
                ans = seller;
            }
        }

        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(ans);
        return sellerResponseDto;
    }

    @Override
    public SellerResponseDto sellerSellingCostliestProduct() {

        int price = 0;
        Seller ans = null;
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            for(Product product : products){
                if(product.getPrice() > price){
                    price = product.getPrice();
                    ans = seller;
                }
            }
        }

        return SellerTransformer.SellerToSellerResponseDto(ans);
    }

    @Override
    public SellerResponseDto sellerSellingCheapestProduct() {

        int price = Integer.MAX_VALUE;
        Seller ans = null;
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            for(Product product : products){
                if(product.getPrice() < price){
                    price = product.getPrice();
                    ans = seller;
                }
            }
        }

        return SellerTransformer.SellerToSellerResponseDto(ans);
    }
}
