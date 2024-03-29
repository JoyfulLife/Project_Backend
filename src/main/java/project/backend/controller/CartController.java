package project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import project.backend.service.CartService;
import project.backend.service.CartServiceImpl;
import project.backend.util.ExceptionUtils;
import project.backend.vo.AdvertisingVO;
import project.backend.vo.CartVO;
import project.backend.vo.ClientVO;

import java.util.List;
import java.util.Locale;

@Controller
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/cart/insertCart", method = RequestMethod.POST)
    @ResponseBody
    public AdvertisingVO insertCart(Locale locale, @RequestBody AdvertisingVO advertisingVO) {

        try {
            cartService.insertCart(advertisingVO);

            return advertisingVO;
        }catch (Exception e){
            new ExceptionUtils(advertisingVO);

            return advertisingVO;
        }
    }

    @RequestMapping(value = "/cart/cartList", method = RequestMethod.POST)
    @ResponseBody
    public CartServiceImpl.cartListAndCount getCartList(Locale locale, @RequestBody CartVO cartVO){

        CartServiceImpl.cartListAndCount res;

        try {
            res = cartService.getCartList(cartVO);

            return res;
        }catch (Exception e){
            new ExceptionUtils(cartVO);

            return new CartServiceImpl.cartListAndCount(cartVO.getFailMessage());
        }
    }

    @RequestMapping(value = "/cart/deleteCartList", method = RequestMethod.POST)
    @ResponseBody
    public CartVO deleteCartList(Locale locale, @RequestBody List<AdvertisingVO> advertisingVO, CartVO errorVO){

        try {
            CartVO cartVO = new CartVO();
            cartVO = cartService.deleteCartList(advertisingVO);

            return cartVO;
        }catch (Exception e){
            new ExceptionUtils(errorVO);

            return errorVO;
        }
    }

}
