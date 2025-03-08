package kr.co.devs32.web.controller;


import kr.co.devs32.web.response.ApiResponse;
import kr.co.devs32.web.service.InvitationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/invitation")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;

    //초대장 조회
    @GetMapping("{id}")
    public ApiResponse getInvitation(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(invitationService.findInvitation(id));
    }
}
