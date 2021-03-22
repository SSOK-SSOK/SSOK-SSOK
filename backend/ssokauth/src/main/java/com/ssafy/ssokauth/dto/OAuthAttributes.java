package com.ssafy.ssokauth.dto;

import com.ssafy.ssokauth.domain.user.Role;
import com.ssafy.ssokauth.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
   private Map<String, Object> attributes;
   private String nameAttributeKey;
   private String name;
   private String email;
   private String picture;

   @Builder
   public OAuthAttributes(Map<String, Object> attributes,
                          String nameAttributeKey, String name,
                          String email, String picture) {
       this.attributes = attributes;
       this.nameAttributeKey= nameAttributeKey;
       this.name = name;
       this.email = email;
       this.picture = picture;
   }

   public static OAuthAttributes of(String registrationId,
                                    String userNameAttributeName,
                                    Map<String, Object> attributes) {
       return ofGoogle(userNameAttributeName, attributes);
   }

   private static OAuthAttributes ofGoogle(String userNameAttributeName,
                                           Map<String, Object> attributes) {
       return OAuthAttributes.builder()
               .name((String) attributes.get("name"))
               .email((String) attributes.get("email"))
               .picture((String) attributes.get("picture"))
               .attributes(attributes)
               .nameAttributeKey(userNameAttributeName)
               .build();
   }


   // 생성하는 시점은 처음 가입할 때 기본권한을 USER로 부여
   public User toEntity() {
       return User.builder()
               .name(name)
               .email(email)
               .picture(picture)
               .role(Role.USER)
               .build();
   }
}