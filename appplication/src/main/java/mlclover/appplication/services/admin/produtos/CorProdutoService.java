package mlclover.appplication.services.admin.produtos;

import mlclover.appplication.entities.admin.produtos.Cor;
import mlclover.appplication.entities.admin.produtos.CorProduto;
import mlclover.appplication.entities.admin.produtos.Produto;
import mlclover.appplication.repositories.admin.produtos.CorProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CorProdutoService {

    @Autowired
    CorProdutoRepository repository;

    @Autowired
    CorService corService;

    public void cadastroImagem(Produto produto, String hexadecimal, String urlImagem) {
        CorProduto corProduto = new CorProduto();

        Cor cor = corService.buscarCorPorHexadecimal(hexadecimal);

        corProduto.setCorId(cor);
        corProduto.setProdutoId(produto);

        corProduto.addImagem(urlImagem);

        repository.save(corProduto);
    }
}
