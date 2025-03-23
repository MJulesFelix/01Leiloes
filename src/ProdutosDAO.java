
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;

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
                    stmt.executeUpdate();  

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
            
            
        public ArrayList<ProdutosDTO> listarProdutos()
        {
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            ArrayList<ProdutosDTO> lista = new ArrayList<>(); // Inicializando a lista

            try
            {
                conn = new conectaDAO().connectDB();

                if (conn == null)
                {
                    JOptionPane.showMessageDialog(null, "Erro: Conexão com o banco de dados falhou.");
                    return lista; // Retornando lista vazia, pois a conexão falhou
                }

                // SQL para selecionar todos os produtos
                String sql = "SELECT * FROM produtos";
                stmt = conn.prepareStatement(sql); 

                rs = stmt.executeQuery(); // Executando a consulta

                // Preenchendo a lista com os dados do ResultSet
                while (rs.next())
                {
                    ProdutosDTO pro = new ProdutosDTO();
                    pro.setId(rs.getInt("id")); 
                    pro.setNome(rs.getString("nome"));
                    pro.setValor(rs.getInt("valor"));
                    pro.setStatus(rs.getString("status"));

                    lista.add(pro);
                }
            }
            catch (Exception e)
            {
                System.out.println("Erro ao pesquisar tabela Produtos: " + e.getMessage());
            }
            finally
            {
                try
                {
                    if (rs != null)
                    {
                        rs.close();
                    }
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
                    System.out.println("Erro ao fechar recursos: " + e.getMessage());
                }
            }

            return lista; // Retornando a lista preenchida ou vazia
    } 
}

