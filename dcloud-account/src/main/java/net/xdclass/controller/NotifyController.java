package net.xdclass.controller;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.NotifyService;
import net.xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/v1")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    /**
     * 测试验证码接口
     * @return
     */
    @GetMapping("send_code")
    public JsonData sendCode(){

        notifyService.testSend();

        return JsonData.buildSuccess();
    }
}
