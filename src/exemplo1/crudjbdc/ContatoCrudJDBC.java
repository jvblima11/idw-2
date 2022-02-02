/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exemplo1.crudjbdc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jvbli
 */
public class ContatoCrudJDBC {
    
    public void Salvar(Contato contato){
        Connection conexao = this.geraConexao();
        PreparedStatement insereSt = null;
        
        String sql ="insert into contato(nome, telefone, email, dt_cad,obs,cpf) value(?, ?, ?, ?, ?, ?)";
        
        try{
            insereSt = conexao.prepareStatement(sql);
            insereSt.setString(1, contato.getNome());
            insereSt.setString(2, contato.getTelefone());
            insereSt.setString(3, contato.getEmail());
            insereSt.setDate(4, contato.getDataCadastro());
            insereSt.setString(5, contato.getObservacao());
            insereSt.setString(6, contato.getCpf());
            insereSt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Erro ao concluir contato no salvar. Mensagem: " + e.getMessage());
        } finally {
            try{
                insereSt.close();
                conexao.close();
            } catch (Throwable e){
                System.out.println("Erro ao fechar operação de insere. Mensagem: " + e.getMessage());
            }
            
            
        }
    }
    
    //Metodo de atualizar
    public void atualizar(Contato contato){
        Connection conexao = this.geraConexao();
        PreparedStatement atualizaSt = null;
        
        //Aqui ñ utilizamos o campo data de cadastro
        String sql = "update contato set nome=?, telefone=?, email=?, obs=?, Where codigo=?,cpf=?";
        
        try{
            atualizaSt = conexao.prepareStatement(sql);
            atualizaSt.setString(1, contato.getNome());
            atualizaSt.setString(2, contato.getTelefone());
            atualizaSt.setString(3, contato.getEmail());
            atualizaSt.setString(4, contato.getObservacao());
            atualizaSt.setInt(5, contato.getCodigo().intValue());
            atualizaSt.setString(6, contato.getCpf());
            atualizaSt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Erro ao atualizar contato. Message: "+ e.getMessage());
        }finally {
            try{
                atualizaSt.close();
                conexao.close();
            } catch (Throwable e){
                    System.out.println("Erro ao fechar operação de atualização. Message: "+ e.getMessage());
                
            }
        }
    }
    
    //Metodo de excluir
    public void excluir(Contato contato){
        Connection conexao = this.geraConexao();
        PreparedStatement exluiSt = null;
        
        //Aqui ñ utilizamos o campo data de cadastro
        String sql = "update contato set nome=?, telefone=?, email=?, obs=?, Where codigo=?,cpf =?";
        
        try{
            exluiSt = conexao.prepareStatement(sql);
            exluiSt.setInt(1, contato.getCodigo().intValue());
            exluiSt.executeUpdate();
        } catch(SQLException e){
            System.out.println("Erro ao excluir contato. Message: "+ e.getMessage());
        }finally {
            try{
                exluiSt.close();
                conexao.close();
            } catch (Throwable e){
                    System.out.println("Erro ao fechar operação de exclusão. Message: "+ e.getMessage());
                
            }
        }
    }
    
    //metodo da Lista
    public List<Contato> listar(){
        Connection conexao = this.geraConexao();
        List<Contato> contatos = new ArrayList<Contato>();
        Statement consulta = null;
        ResultSet resultado = null;
        Contato contato = null;
        String sql = "select * from contato";
        try{
            consulta = conexao.createStatement();
            resultado = consulta.executeQuery(sql);
            while(resultado.next()){
                contato = new Contato();
                contato.setCodigo(new Integer(resultado.getInt("codigo")));
                contato.setNome(resultado.getString("nome"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setEmail(resultado.getString("email"));
                contato.setDataCadastro(resultado.getDate("dt_cad"));
                contato.setObservacao(resultado.getString("obs"));
                contato.setCpf(resultado.getString("cpf"));
                contatos.add(contato);
            }
        } catch (SQLException e){
            System.out.println("Erro ao buscar codigo de contato. Message: "+ e.getMessage());
        } finally{
            try{
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (Throwable e){
                System.out.println("Erro ao fechar operações de consulta. Message: "+ e.getMessage());
            }
        }
        return contatos;
    }
    
    public Contato buscaContato(int valor){
        Connection conexao = this.geraConexao();
        PreparedStatement consulta = null;
        ResultSet resultado = null;
        Contato contato = null;
        
        String sql ="select * from contato where codigo = ?";
        
        try{
            consulta = conexao.prepareStatement(sql);
            consulta.setInt(1, valor);
            resultado = consulta.executeQuery();
            
            if(resultado.next()){
                contato = new Contato();
                contato.setCodigo(new Integer(resultado.getInt("codigo")));
                contato.setNome(resultado.getString("nome"));
                contato.setTelefone(resultado.getString("telefone"));
                contato.setEmail(resultado.getString("email"));
                contato.setDataCadastro(resultado.getDate("dt_cad"));
                contato.setObservacao(resultado.getString("obs"));
                contato.setCpf(resultado.getString("cpf"));
            }
        } catch(SQLException e){
            System.out.println("Erro ao buscar código do contato. Mensagem: "+ e.getMessage());
        } finally{
            try{ 
                consulta.close();
                resultado.close();
                conexao.close();
            } catch (Throwable e){
                System.out.println("Erro ao fechar operação de consulta. Mensagem: "+ e.getMessage());
            }
        }
    return contato;
    }
    
    public Connection geraConexao(){
        Connection conexao = null;
        
        try{
            //Registrando a classe JDBC no sistema em tempo de execução
            Class.forName("com.mysql.jdbc.Driver");
            String url ="jdbc:mysql://localhost:3307/agenda";
            String usuario = "root";
            String senha ="123";
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch(ClassNotFoundException e){
                System.out.println("Classe não encontrado. Erro: "+ e.getMessage());            
        } catch (SQLException e){
                System.out.println("Classe não encontrado. Erro: "+ e.getMessage());
        }
        return conexao;
    }
    
    public static void main(String[] args){
        System.out.println("Entrou no main");
        ContatoCrudJDBC contatoCRUD_JDBC = new ContatoCrudJDBC();
        
        Contato beltrano = new Contato();
        beltrano.setNome("Joao Vitor");
        beltrano.setDataCadastro(new Date(System.currentTimeMillis()));
        beltrano.setEmail("jv.blima11@gmail.com");
        beltrano.setTelefone("(69) 99999-9999");
        beltrano.setObservacao("Novo cliene");
        beltrano.setCpf("049.708.772-38");
        System.out.println(beltrano);
        contatoCRUD_JDBC.Salvar(beltrano);
        
        Contato fulano = new Contato();
        fulano.setNome("kleiton");
        fulano.setDataCadastro(new Date(System.currentTimeMillis()));
        fulano.setEmail("algumacoisa@gmail.com");
        fulano.setTelefone("(69) 99999-9999");
        fulano.setObservacao("Cliente antigo");
        fulano.setCpf("454,455,455-55");
        contatoCRUD_JDBC.Salvar(fulano);
        
        System.out.println("Contatos cadastrados: " + contatoCRUD_JDBC.listar().size());
    }
}
