package com.residence.ecommerce.entity;

import java.time.chrono.JapaneseChronology;

public class Cursos {
    private String LogicaDeProgramação;
    private String BancoDeDados;
    private String POO;
    private String FrontEndEssencial;
    private String Api;
    private String React;
    private String AI;

    public String getLogicaDeProgramação() {
        return LogicaDeProgramação;
    }

    public void setLogicaDeProgramação(String logicaDeProgramação) {
        LogicaDeProgramação = logicaDeProgramação;
    }

    public String getBancoDeDados() {
        return BancoDeDados;
    }

    public void setBancoDeDados(String bancoDeDados) {
        BancoDeDados = bancoDeDados;
    }

    public String getPOO() {
        return POO;
    }

    public void setPOO(String POO) {
        this.POO = POO;
    }

    public String getFrontEndEssencial() {
        return FrontEndEssencial;
    }

    public void setFrontEndEssencial(String frontEndEssencial) {
        FrontEndEssencial = frontEndEssencial;
    }

    public String getApi() {
        return Api;
    }

    public void setApi(String api) {
        Api = api;
    }

    public String getReact() {
        return React;
    }

    public void setReact(String react) {
        React = react;
    }

    public String getAI() {
        return AI;
    }

    public void setAI(String AI) {
        this.AI = AI;
    }
}
