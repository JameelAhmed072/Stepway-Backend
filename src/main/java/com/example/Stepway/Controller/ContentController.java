package com.example.Stepway.Controller;


import com.example.Stepway.Service.impl.ContentServiceImpl;
import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.ContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class ContentController {

    @Autowired
    ContentServiceImpl contentServiceImpl;

    @PostMapping("/content")
    public ResponseEntity<ContentDto> addContent(@Valid @RequestBody ContentDto contentDto){

        ContentDto createContent = contentServiceImpl.createContent(contentDto);

        return ResponseEntity.ok(createContent);
    }
    @GetMapping("/content/{id}")
    public ResponseEntity<ContentDto> getContentById(@Valid @PathVariable Long id){

        ContentDto contentDto = contentServiceImpl.getContentsById(id);

        return ResponseEntity.ok(contentDto);
    }

    @GetMapping("/allContent")
    public ResponseEntity<List<ContentDto>> getAllContent(){

        List<ContentDto> contents = contentServiceImpl.getALlContents();

        return ResponseEntity.ok(contents);
    }

    @PutMapping("/content/{id}")
    public ResponseEntity<ContentDto> updateContent(@Valid @RequestBody ContentDto contentDto, @PathVariable Long id){

        ContentDto content = contentServiceImpl.updateContentById(id,contentDto);

        return ResponseEntity.ok(content);
    }

    @DeleteMapping("/deleteContent/{id}")
    public ResponseEntity<ContentDto> deleteContentById(@PathVariable Long id){
        contentServiceImpl.deleteContentById(id);
        return ResponseEntity.noContent().build();
    }
}
