package br.com.samueljunnior.module.user.service;

import br.com.samueljunnior.core.exceptions.BusinessException;
import br.com.samueljunnior.module.user.dto.UserCreateDTO;
import br.com.samueljunnior.module.user.dto.UserDTO;
import br.com.samueljunnior.module.user.dto.UserFilterDTO;
import br.com.samueljunnior.module.user.entity.UserTypeEntity;
import br.com.samueljunnior.module.user.mapper.UserCreateMaper;
import br.com.samueljunnior.module.user.mapper.UserFilterMaper;
import br.com.samueljunnior.module.user.mapper.UserMaper;
import br.com.samueljunnior.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMaper userMaper;
    private final UserCreateMaper userCreateMaper;
    private final UserFilterMaper userFilterMaper;

    public UserDTO createUser(UserCreateDTO user){
        final var documentWithoutDots = StringUtils.getDigits(user.document());

        final var findOldByDocumentoOrEmail = userRepository.findByDocumentOrEmail(documentWithoutDots, user.email());
        if(findOldByDocumentoOrEmail.isPresent()){
            throw new BusinessException("Document or E-mail already exists");
        }

        final var userEntity = userCreateMaper.toEntity(user);
        userEntity.setDocument(documentWithoutDots);
        userEntity.setType(UserTypeEntity.builder().id(user.type().getId().longValue()).build());

        return userMaper.toDto(userRepository.save(userEntity));
    }

    public List<UserDTO> findUsersByFilter(UserFilterDTO filter) {
        final var entity = userFilterMaper.toEntity(filter);

        final var exm = ExampleMatcher
                .matchingAny()
                .withIgnoreNullValues()
                .withMatcher("document", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.exact());

        final var ex = Example.of(entity, exm);

        return userMaper.toDto(userRepository.findAll(ex, Sort.by(Sort.Direction.ASC, "id")));
    }
}
