package kr.co.devs32.web.controller;


import jakarta.persistence.EntityNotFoundException;
import kr.co.devs32.web.domain.GuestBook;
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
    @GetMapping("{id}")
    public ApiResponse getInvitation(@PathVariable(name = "id") Long id) {
        return ApiResponse.success(invitationService.findInvitation(id));
    }

    //게스트 참석여부 저장
    @PostMapping("{id}/guests")
    public ResponseEntity<ApiResponse<String>> saveGuest(@PathVariable(name = "id") Long id, @RequestBody GuestRequestDto dto) {
        //이름, 참석인원, 동행인(여러명 가능), 식사여부(예정,안함,미정)
        dto.setInvuId(id);
        guestService.save(dto);
        ApiResponse<String> response = ApiResponse.successCreated("Created");
        return ResponseEntity.status(201).body(response);
    }

    //게스트 리스트 조회
    @GetMapping("{id}/guests")
    public ApiResponse getGuestsList(@PathVariable(name = "id") Long id){
        return ApiResponse.success(guestService.findAllByInvuId(id));
    }

    //특정 게스트 정보 조회
    @GetMapping("{id}/guests/{name}")
    public ApiResponse getGuestInfo(@PathVariable(name = "id") Long id, @PathVariable(name = "name") String name){
        return ApiResponse.success(guestService.findGuestByInvuIdAndUniqueName(id, name));
    }

    //방명록 저장
    @PostMapping("{id}/guestBooks")
    public ResponseEntity<ApiResponse<String>> saveGuestBook(@PathVariable(name = "id") Long id , @RequestBody GuestBookRequestDto dto) {
        dto.setInvuId(id);
        guestBookService.save(dto);
        ApiResponse<String> response = ApiResponse.successCreated("Created");
        return ResponseEntity.status(201).body(response);
    }

    //방명록 리스트 조회
    @GetMapping("{id}/guestBooks")
    public ApiResponse getGuestBookList(@PathVariable(name = "id") Long id){
        return ApiResponse.success(guestBookService.findAllGuestBookByInvuId(id));
    }

    //방명록 단건 조회
    @GetMapping("{id}/guestBooks/{bookId}")
    public ApiResponse getGuestBookInfo(@PathVariable(name = "id") Long invuId , @PathVariable(name = "bookId") Long bookId) {
        return ApiResponse.success(guestBookService.findGuestBookByInvuIdAndId(invuId, bookId));
    }

    //방명록 수정
    @PostMapping("{id}/guestBooks/{bookId}")
    public ApiResponse updateGuestBooks(@PathVariable(name = "id") Long invuId, @PathVariable(name = "bookId") Long bookId, @RequestBody GuestBookRequestDto dto){
        guestBookService.updateGuestBookMessage(invuId, bookId, dto);
        return ApiResponse.success("방명록 수정이 완료되었습니다.");
    }

    @PostMapping("{id}/guestBooks/delete/{bookId}")
    public ApiResponse deleteGuestBook(@PathVariable(name = "id") Long invuId, @PathVariable(name = "bookId") Long bookId, @RequestBody GuestBookRequestDto dto) {
        guestBookService.deleteGuestBook(invuId, bookId, dto);
        return ApiResponse.success("방명록 삭제가 완료되었습니다.");
    }




}
