package api.finances.mapper;

import api.finances.model.Wallet;
import api.finances.model.dto.WalletDto;
import api.finances.model.request.WalletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WalletMapper {

    
    @Autowired
    private ModelMapper modelMapper;

    public WalletDto toDto(Wallet wallet) {
        return this.modelMapper.map(wallet, WalletDto.class);
    }

    public Wallet toModel(WalletDto walletDto) {
        return this.modelMapper.map(walletDto, Wallet.class);
    }

    public Wallet toDomainModel(WalletRequest walletRequest) {
        return this.modelMapper.map(walletRequest, Wallet.class);
    }

    public List<WalletDto> toListDto(List<Wallet> walletList) {
        return walletList.stream().map(this::toDto).collect(Collectors.toList());
    }

}

