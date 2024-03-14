package api.finances.controller;


import api.finances.mapper.WalletMapper;
import api.finances.model.Wallet;
import api.finances.model.dto.WalletDto;
import api.finances.model.request.WalletRequest;
import api.finances.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletMapper walletMapper;


    @GetMapping("/user/{id}")
    public List<WalletDto> getWallets(@PathVariable Long id) {

        List<Wallet> wallets = this.walletService.getWallets(id);

        return this.walletMapper.toListDto(wallets);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody WalletRequest walletRequest) {

        Wallet wallet = this.walletMapper.toDomainModel(walletRequest);

        this.walletService.create(wallet);

    }

    @GetMapping("/{id}/user/{user_id}")
    public WalletDto getWallet(@PathVariable Long id, @PathVariable Long user_id) {

        Wallet wallet = this.walletService.findByUserId(id, user_id);

        return this.walletMapper.toDto(wallet);
    }

    @PutMapping("/{id}")
    public WalletDto update(@PathVariable Long id,
                            @Valid @RequestBody WalletRequest walletRequest) {

        Long user_id = walletRequest.getUser().getId();

        Wallet wallet = this.walletMapper.toDomainModel(walletRequest);

        this.walletService.update(id, user_id, wallet);

        Wallet walletUpdated = this.walletService.findByUserId(id, user_id);

        return this.walletMapper.toDto(walletUpdated);
    }

    @DeleteMapping("/{id}/user/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id, @PathVariable Long user_id) {
        this.walletService.delete(id, user_id);
    }
}
