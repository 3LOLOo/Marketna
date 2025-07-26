package com.example.E_Commerce_API.service;
import com.example.E_Commerce_API.model.Cart;
import com.example.E_Commerce_API.model.User;
import com.example.E_Commerce_API.model.UserPrincipal;
import com.example.E_Commerce_API.model.Wallet;
import com.example.E_Commerce_API.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public User SaveUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        Cart cart = new Cart();
        Wallet wallet = new Wallet();

        // Must set user on the owning side
        cart.setUser(user);
        wallet.setUser(user);

        // Set for bidirectional access (inverse side)
        user.setCart(cart);
        user.setWallet(wallet);

        return repo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated())
        {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // ✅ correct source
            return jwtService.generateToken((UserPrincipal) userDetails); // ✅ includes real role
        }
        return "fail";
    }
}
