package kr.co.devs32.web.controller;


import kr.co.devs32.web.dto.GuestBookRequestDto;
import kr.co.devs32.web.dto.GuestRequestDto;
import kr.co.devs32.web.response.ApiResponse;
import kr.co.devs32.web.service.GuestBookService;
import kr.co.devs32.web.service.GuestService;
import kr.co.devs32.web.service.InvitationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/invitation")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;
    private final GuestService guestService;

    private final GuestBookService guestBookService;

    //초대장 조회
    @GetMapping("{code}")
    public ApiResponse getInvitation(@PathVariable(name = "code") String code) {
        return ApiResponse.success(invitationService.findInvitation(code));
    }

    //게스트 참석여부 저장
    @PostMapping("{code}/guests")
    public ResponseEntity<ApiResponse<String>> saveGuest(@PathVariable(name = "code") String code, @RequestBody GuestRequestDto dto) {
        //이름, 참석인원, 동행인(여러명 가능), 식사여부(예정,안함,미정)
        dto.setInvuId(invitationService.findInvitationId(code));
        guestService.save(dto);
        ApiResponse<String> response = ApiResponse.successCreated("Created");
        return ResponseEntity.status(201).body(response);
    }

    //게스트 리스트 조회
    @GetMapping("{code}/guests")
    public ApiResponse getGuestsList(@PathVariable(name = "code") String code){
        return ApiResponse.success(guestService.findAllByInvuId(invitationService.findInvitationId(code)));
    }

    //특정 게스트 정보 조회
    @GetMapping("{code}/guests/{name}")
    public ApiResponse getGuestInfo(@PathVariable(name = "code") String code, @PathVariable(name = "name") String name){
        return ApiResponse.success(guestService.findGuestByInvuIdAndUniqueName(invitationService.findInvitationId(code), name));
    }

    //방명록 저장
    @PostMapping("{code}/guestBooks")
    public ResponseEntity<ApiResponse<String>> saveGuestBook(@PathVariable(name = "code") String code , @RequestBody GuestBookRequestDto dto) {
        dto.setInvuId(invitationService.findInvitationId(code));
        guestBookService.save(dto);
        ApiResponse<String> response = ApiResponse.successCreated("Created");
        return ResponseEntity.status(201).body(response);
    }

    //방명록 리스트 조회
    @GetMapping("{code}/guestBooks")
    public ApiResponse getGuestBookList(@PathVariable(name = "code") String code){
        return ApiResponse.success(guestBookService.findAllGuestBookByInvuId(invitationService.findInvitationId(code)));
    }

    //방명록 단건 조회
    @GetMapping("{code}/guestBooks/{bookId}")
    public ApiResponse getGuestBookInfo(@PathVariable(name = "code") String code , @PathVariable(name = "bookId") Long bookId) {
        return ApiResponse.success(guestBookService.findGuestBookByInvuIdAndId(invitationService.findInvitationId(code), bookId));
    }

    //방명록 수정
    @PostMapping("{code}/guestBooks/{bookId}")
    public ApiResponse updateGuestBooks(@PathVariable(name = "code") String code, @PathVariable(name = "bookId") Long bookId, @RequestBody GuestBookRequestDto dto){
        guestBookService.updateGuestBookMessage(invitationService.findInvitationId(code), bookId, dto);
        return ApiResponse.success("방명록 수정이 완료되었습니다.");
    }

    //방명록 삭제
    @PostMapping("{code}/guestBooks/delete/{bookId}")
    public ApiResponse deleteGuestBook(@PathVariable(name = "code") String code, @PathVariable(name = "bookId") Long bookId, @RequestBody GuestBookRequestDto dto) {
        guestBookService.deleteGuestBook(invitationService.findInvitationId(code), bookId, dto);
        return ApiResponse.success("방명록 삭제가 완료되었습니다.");
    }

}
