package br.com.mycompany.chaponibus.admin.model;

public class Rota {

    private int idRota;
    private String nomeRota;
    private String descricao;
    private boolean ativa;
    private int idOnibus;

    public Rota() {
    }

    public Rota(String nomeRota,
                String descricao,
                boolean ativa,
                int idOnibus) {

        this.nomeRota = nomeRota;
        this.descricao = descricao;
        this.ativa = ativa;
        this.idOnibus = idOnibus;
    }

    public int getIdRota() {
        return idRota;
    }

    public void setIdRota(int idRota) {
        this.idRota = idRota;
    }

    public String getNomeRota() {
        return nomeRota;
    }

    public void setNomeRota(String nomeRota) {
        this.nomeRota = nomeRota;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public int getIdOnibus() {
        return idOnibus;
    }

    public void setIdOnibus(int idOnibus) {
        this.idOnibus = idOnibus;
    }
    @Override
    public String toString() {
        return nomeRota;
    }
}
