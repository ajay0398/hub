package com.hub.mapper;



import com.hub.dto.SubhubDto;
import com.hub.model.Post;
import com.hub.model.Subhub;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubhubMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subhub.getPosts()))")
    SubhubDto mapSubhubToDto(Subhub subhub);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subhub mapDtoToSubhub(SubhubDto subhubDto);
}
