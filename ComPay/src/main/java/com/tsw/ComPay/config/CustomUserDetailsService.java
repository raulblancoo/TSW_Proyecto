package com.tsw.ComPay.config;

import com.tsw.ComPay.Dto.GroupDto;
import com.tsw.ComPay.Dto.GroupMembersDto;
import com.tsw.ComPay.Dto.UserAuthDto;
import com.tsw.ComPay.Dto.UserDto;
import com.tsw.ComPay.Mapper.GroupMapper;
import com.tsw.ComPay.Mapper.GroupMembersMapper;
import com.tsw.ComPay.Mapper.NewGroupMapper;
import com.tsw.ComPay.Mapper.UserMapper;
import com.tsw.ComPay.Models.UserModel;
import com.tsw.ComPay.Repositories.GroupMembersRepository;
import com.tsw.ComPay.Repositories.GroupRepository;
import com.tsw.ComPay.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final GroupMembersRepository groupMembersRepository;

    private final GroupMembersMapper groupMembersMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userMapper.toDto(userRepository.findByEmail(email));


        if(user != null) {
            List<GroupMembersDto> groupsMembers = groupMembersMapper.toListDto(groupMembersRepository.findByUser(userMapper.toEntity(user)));

            List<GroupDto> groups = groupsMembers.stream().map(GroupMembersDto::getGroup).collect(Collectors.toList());

            UserAuthDto userAuth = new UserAuthDto(user, groups);

            return userAuth;
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
