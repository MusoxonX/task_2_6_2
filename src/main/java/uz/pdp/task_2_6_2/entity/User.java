package uz.pdp.task_2_6_2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;//qachon yaratilganligi

    @UpdateTimestamp
    private Timestamp updateAt; //qachon tahrirlanganligi

    @ManyToMany
    private Set<Role> role;

    private boolean accountNonExpired = true;// bu userning amal qilish muddati utgan yoki utmaganligini bildiradi

    private boolean accountNonLocked = true;// bu userning bloklangan yoki bloklanmaganini bildiradi

    private boolean credentialNonAxpired = true;//bu userning

    private boolean enabled;//nu user yoniqmi

    private String emailCode;



    //---------- user details ning o'zining majburiy methodlari------------//

    // bu userning huquqlari
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {return this.role;}

    //bu userning unsernameni qaytaruchi method
    @Override
    public String getUsername() {
        return this.email;
    }

    // bu userning amal qilish muddati haqida
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    // bu userning blok langanligini qaytaradi
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    // bu userning ishonchliligini muddati qaytaradi
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialNonAxpired;
    }

    // accountning yoniq yoki o;chiqligini bildiradi
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
