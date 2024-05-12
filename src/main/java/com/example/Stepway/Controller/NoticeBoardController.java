package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.NoticeBoardImpl;
import com.example.Stepway.dto.NoticeBoardDto;
import com.example.Stepway.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class NoticeBoardController {

    @Autowired
    NoticeBoardImpl noticeBoard;


    @PostMapping("/noticeboard")
    public ResponseEntity<NoticeBoardDto> addNotice(@Valid @RequestBody NoticeBoardDto noticeBoardDto){

        NoticeBoardDto createNotice = noticeBoard.createNotice(noticeBoardDto);

        return ResponseEntity.ok(createNotice);
    }

    @GetMapping("/allNotices")
    public ResponseEntity<List<NoticeBoardDto>> getAllNotices(){
        List<NoticeBoardDto> allNotices = noticeBoard.getALlNotices();
        return ResponseEntity.ok(allNotices);
    }
}
