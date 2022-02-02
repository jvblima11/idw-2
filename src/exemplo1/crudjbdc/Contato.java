/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exemplo1.crudjbdc;

import java.sql.Date;

/**
 *
 * @author jvbli
 */
public class Contato {
    private Integer codigo;
    private String nome,telefone,email,observacao,cpf;
    private Date dataCadastro;

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getObservacao() {
        return observacao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    public String getCpf(){
        return cpf;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public Contato(Integer codigo, String nome, String telefone, String email, String observacao, Date dataCadastro, String cpf) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.observacao = observacao;
        this.dataCadastro = dataCadastro;
        this.cpf = cpf;
    }
    
    public Contato() {
        this.codigo = -1;
        this.nome = "";
        this.telefone = "";
        this.email = "";
        this.observacao = "";
        this.dataCadastro = null;
        this.cpf = "";
    }
    
}
