package com.zhaojj11.clockwork.user.entity.transformer;


import com.zhaojj11.clockwork.user.entity.dto.LoginUserDTO;
import com.zhaojj11.clockwork.user.entity.vo.request.LoginUserRequestVO;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * @author zhaojj11
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserTransformer {

    /**
     * request vo to dto
     *
     * @param vo vo
     * @return dto
     */
    LoginUserDTO toLoginUser(LoginUserRequestVO vo);
}
