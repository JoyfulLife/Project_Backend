package project.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import project.backend.service.ClientService;
import project.backend.util.ExceptionUtils;
import project.backend.vo.ClientVO;
import project.backend.vo.ResponseVO;
import project.backend.vo.SignUpVO;

import java.util.List;
import java.util.Locale;

@Controller
public class ClientController{
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @RequestMapping(value = "/client/clientInfo", method = RequestMethod.POST)
    @ResponseBody
    public ClientVO validclient(Locale locale, @RequestBody ClientVO clientVO) {

        //로그인할때 등록된 인원인지 확인
        ClientVO res = new ClientVO();

        try {
            res = clientService.selectValidClient(clientVO);

            return res;
        }catch (Exception e){
            new ExceptionUtils(clientVO);

            return clientVO;
        }
    }

    @RequestMapping(value = "/client/saveClient", method = RequestMethod.POST)
    @ResponseBody
    public SignUpVO saveclient(Locale locale, @RequestBody SignUpVO signUpVO) {

        //회원가입시 이미 등록된 User_ID가 있는지 확인!
        //중복된 User_ID가 없으면 회원 등록을 한다.
        try{
            clientService.DeduplicationUser_ID(signUpVO);

            return signUpVO;
        }catch (Exception e){
            new ExceptionUtils(signUpVO);

            return signUpVO;
        }

    }
}





//    //get 으로 받을거면 @ModelAttribute
//    @RequestMapping(value = "/ado/client/clientInfo", method = RequestMethod.GET)
//    @ResponseBody
//    public List<ClientVO> getclient(Locale locale, @ModelAttribute ClientVO ClientVO) {
//
//        List<ClientVO> clientVO = clientService.getAllDataList();
//
//        return clientVO;
//    }
//master branch push test 001 집 노트북 push 확인!!!!!
