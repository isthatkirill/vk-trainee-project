package isthatkirill.vkproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kirill Emelyanov
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {


    @GetMapping("/test")
    public String test() {
        return "Ok.";
    }


}
