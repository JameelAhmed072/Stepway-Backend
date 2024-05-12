package com.example.Stepway.Service;

import com.example.Stepway.dto.CertificationDto;
import com.example.Stepway.dto.ContentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContentService {

    List<ContentDto> getALlContents();


    public ContentDto getContentsById(Long id);

    public ContentDto createContent(ContentDto contentDto);

    ContentDto updateContentById(Long id,ContentDto contentDto);

    public void deleteContentById(Long id);
}
