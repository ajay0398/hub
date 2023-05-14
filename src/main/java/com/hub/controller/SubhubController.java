package com.hub.controller;


import com.hub.Service.SubredditService;
import com.hub.dto.SubhubDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subhub")
@AllArgsConstructor
@Slf4j
public class SubhubController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubhubDto> createSubreddit(@RequestBody SubhubDto subhubDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subredditService.save(subhubDto));
    }

    @GetMapping
    public ResponseEntity<List<SubhubDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubhubDto> getSubreddit(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }
}
