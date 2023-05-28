package com.hub.Service;

import com.hub.dto.SubhubDto;
import com.hub.exceptions.SpringHubException;
import com.hub.mapper.SubhubMapper;
import com.hub.model.Subhub;

import com.hub.repository.SubhubRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class SubHubService {

    private final SubhubRepository subhubRepository;
    private final SubhubMapper subhubMapper;

    @Transactional
    public SubhubDto save(SubhubDto subhubDto) {
        Subhub save = subhubRepository.save(subhubMapper.mapDtoToSubhub(subhubDto));
        subhubDto.setId(save.getId());
        return subhubDto;
    }

    @Transactional(readOnly = true)
    public List<SubhubDto> getAll() {
        return subhubRepository.findAll()
                .stream()
                .map(subhubMapper::mapSubhubToDto)
                .collect(toList());
    }

    public SubhubDto getSubreddit(Long id) {
        Subhub subhub = subhubRepository.findById(id)
                .orElseThrow(() -> new SpringHubException("No subreddit found with ID - " + id));
        return subhubMapper.mapSubhubToDto(subhub);
    }
}
