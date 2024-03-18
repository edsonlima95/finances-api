package api.finances.service;

import api.finances.exception.customExceptions.NotFoundException;
import api.finances.model.Category;
import api.finances.model.User;
import api.finances.model.Wallet;
import api.finances.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserService userService;


    public List<Wallet> getWallets(Long user_id) {

        User user = this.userService.findById(user_id);

        return user.getWallets();

    }

    public void create(Wallet wallet) {

        this.userService.findById(wallet.getUser().getId());

        this.walletRepository.save(wallet);
    }

    public Wallet update(Long id, Long user_id, Wallet wallet) {

        this.userService.findById(wallet.getUser().getId());

        this.walletRepository.findByUserId(id, user_id).orElseThrow(() -> new NotFoundException(id));

        wallet.setId(id);

       return this.walletRepository.save(wallet);
    }

    public Wallet findByUserId(Long id, Long user_id) {
        return this.walletRepository.findByUserId(id, user_id).orElseThrow(() -> new NotFoundException(id));
    }

    public Wallet findById(Long id) {
        return this.walletRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(Long id, Long user_id) {

        this.findByUserId(id, user_id);

        this.walletRepository.deleteById(id);

    }


}
