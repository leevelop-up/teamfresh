package com.example.teamfresh.domain.product.controller;

import com.example.teamfresh.common.dto.response.ApiResponse;
import com.example.teamfresh.common.enums.ReturnCode;
import com.example.teamfresh.domain.product.dto.ProductRegisterParam;
import com.example.teamfresh.domain.product.dto.ProductRegisterRequest;
import com.example.teamfresh.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ApiResponse<?> register(@RequestBody ProductRegisterRequest productRegisterRequest) {
        ProductRegisterParam param = productRegisterRequest.toParam();
        productService.registerProduct(param);
        return ApiResponse.of(ReturnCode.SUCCESS);
    }

}
