package servico.client.dto;

public class ClientDTO {

    private Long id;
    private String name;
    private String cpf;
    private String habilitacao;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, String habilitacao) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.habilitacao = habilitacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(String habilitacao) {
        this.habilitacao = habilitacao;
    }
}
