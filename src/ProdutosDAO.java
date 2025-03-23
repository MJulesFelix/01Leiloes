
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    
            public void cadastrarProduto(ProdutosDTO produto) 
            {
                // Criando e inicializando a variável de conexão
                Connection conn = null;
                PreparedStatement stmt = null;

                try {
                    // Estabelecendo a conexão
                    conn = new conectaDAO().connectDB();

                    // Verificando se a conexão foi estabelecida corretamente
                    if (conn == null)   
                    {
                        JOptionPane.showMessageDialog(null, "Erro: Conexão com o banco de dados falhou.");
                        return; // Saindo do método, já que não há conexão com o banco de dados
                    }

                    // SQL para inserir os dados na tabela
                    String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";

                    // Preparando a consulta SQL
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, produto.getNome());
                    stmt.setInt(2, produto.getValor());
                    stmt.setString(3, produto.getStatus());

                    // Executando a consulta
                    stmt.executeUpdate();  // Usa executeUpdate() para comandos de inserção

                    // Exibindo a mensagem de sucesso
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                } 
                catch (SQLException e) 
                {
                    // Exibindo erro específico de SQL
                    System.out.println("Erro ao cadastrar Produto: " + e.getMessage());
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar Produto: " + e.getMessage());
                }
                finally
                {
                    // Fechando os recursos no bloco finally para garantir que sejam fechados
                    try 
                    {
                        if (stmt != null) 
                        {
                            stmt.close();
                        }
                        if (conn != null) 
                        {
                            conn.close();
                        }
                    } 
                    catch (SQLException e) 
                    {
                        System.out.println("Erro ao fechar os recursos: " + e.getMessage());
                    }
                }
        }
    
    
        public ArrayList<ProdutosDTO> listarProdutos(){

            return listagem;
        }
    
    
    
        
}

