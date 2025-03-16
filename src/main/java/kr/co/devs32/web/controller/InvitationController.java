package kr.co.devs32.web.controller;


import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.response.ApiResponse;
import kr.co.devs32.web.service.GuestService;
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
    private final GuestService geustService;

    //초대장 조회
    @GetMapping("{id}")
    public ApiResponse getInvitation(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(invitationService.findInvitation(id));
    }

    //게스트 참석여부 저장
    @PostMapping("{id}/guests")
    public ApiResponse saveGuest(@PathVariable(name = "id") Long id, @RequestBody GuestRequestDto dto) {
        //이름, 참석인원, 동행인(여러명 가능), 식사여부(예정,안함,미정)
        return ApiResponse.success(geustService.save(dto));
    }

    //게스트 리스트 조회
    @GetMapping("{id}/guests")
    public ApiResponse getGuestsList(@PathVariable(name = "id") Long id){
        return ApiResponse.success(geustService.findAll());
    }

    //특정 게스트 정보 조회
    @GetMapping("{id}/guests/{name}")
    public ApiResponse getGuestInfo(@PathVariable(name = "id") Long id, @PathVariable(name = "name") String name){
        return ApiResponse.success(geustService.findGuestByUniqueName(name));
    }
}
