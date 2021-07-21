package br.com.gestaotarefas.util;

import br.com.lumi.exception.BaseException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Random;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {

    private static BigDecimal valorTotal;

    private static final Object[][] letras = {
        {"q", "w", "e", "r", "t", "y", "u", "i", "o", "o"},
        {"p", "a", "s", "d", "f", "g", "h", "j", "k", "e"},
        {"l", "z", "x", "c", "v", "b", "n", "m", "q", "s"},
        {"p", "l", "m", "k", "o", "i", "j", "n", "b", "c"},
        {"v", "g", "y", "t", "f", "c", "x", "d", "r", "k"},
        {"e", "s", "z", "a", "q", "w", "s", "x", "c", "t"},
        {"v", "f", "g", "t", "h", "n", "b", "o", "k", "a"},
        {"p", "w", "s", "j", "r", "t", "c", "b", "s", "h"},
        {"l", "a", "t", "m", "s", "p", "r", "h", "d", "j"}};

    

    

    public static Date IncDay(Date data, int dias) {
        Calendar c = new GregorianCalendar();
        c.setTime(data);
        c.add(Calendar.DAY_OF_MONTH, dias);
        return c.getTime();
    }

    public static String senhaRand() {

        String retorno;

        try {
            String numeros = "" + Math.IEEEremainder(Math.random(), Math.random());
            numeros = numeros.substring(numeros.length() - 8);
            int linha = Integer.parseInt(numeros.substring(0, 1));
            int tr1 = Integer.parseInt("" + numeros.charAt(2));
            int tr2 = Integer.parseInt("" + numeros.charAt(5));
            int tr3 = Integer.parseInt("" + numeros.charAt(7));

            numeros = numeros.replaceFirst(("" + tr1), letras[linha][tr1].toString());
            numeros = numeros.replaceFirst(("" + tr2), letras[linha][tr2].toString().toUpperCase());
            numeros = numeros.replaceFirst(("" + tr3), letras[linha][tr3].toString());

            retorno = numeros;

        } catch (Exception e) {
            retorno = "759G4f58";
        }

        return retorno;

    }

    public static String getSenhaAleatoria() {
        Random ran = new Random();

        String[] letras = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        String b = "";
        int num = 8;

        for (int i = 0; i < num; i++) {
            int a = ran.nextInt(letras.length);
            b += letras[a];
        }

        return b;
    }

    public static String erroExcluir(String value) {

        String retorno = "Não foi possovel excluir o registro\n";
        retorno += "porque existem um ou mais registros\n";
        retorno += "vinculado a ele.";
        return retorno;

    }

    public static String formateCnpj(String value) {

        String retorno = "";
        if (value != null && value.length() == 14) {
            retorno = value.substring(0, 2);
            retorno += ".";
            retorno += value.substring(2, 5);
            retorno += ".";
            retorno += value.substring(5, 8);
            retorno += "/";
            retorno += value.substring(8, 12);
            retorno += "-";
            retorno += value.substring(12);
        }
        return retorno;
    }

    private static BigDecimal getValorUnitário(Integer qtdProduto) throws BaseException {

        BigDecimal retorno = new BigDecimal(0);
        double somaQtd = 0;

        if (qtdProduto == 1) {
            retorno = valorTotal;
        } else {

            double unitario = Double.parseDouble(valorTotal.toString());
            somaQtd = unitario / qtdProduto;
            retorno = new BigDecimal(somaQtd);
        }

        return retorno.setScale(2, RoundingMode.HALF_UP);
    }

    public static String truncDecimal(double value) {
        int temp = (int) (value * 100.0);
        value = ((double) temp / 100);
        return formatDecimalObject(value);
    }

    public static String formatDecimalObject(double value) {
        String valor;
        NumberFormat nf = new DecimalFormat("#.##");
        valor = nf.format(arredondar(value));
        return valor.replace(",", ".");
    }

    public static double arredondar(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }

    public static String getRemoveCaracteres(String texto) throws BaseException {

        if (texto == null) {
            return "";
        }

        if (texto.equals("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL")) {
            return texto;
        }

        texto = texto.replace("\n", "");
        texto = texto.replace("\r", "");
        texto = texto.replace("'", "");
        texto = texto.replace("/", "");
        texto = texto.replace(".", "");
        texto = texto.replace("-", "");
        texto = texto.replace("á", "a");
        texto = texto.replace("é", "e");
        texto = texto.replace("í", "i");
        texto = texto.replace("ó", "o");
        texto = texto.replace("ú", "u");
        texto = texto.replace("Á", "A");
        texto = texto.replace("É", "E");
        texto = texto.replace("Í", "I");
        texto = texto.replace("Ó", "O");
        texto = texto.replace("Ú", "U");
        texto = texto.replace("â", "a");
        texto = texto.replace("ê", "e");
        texto = texto.replace("î", "i");
        texto = texto.replace("ô", "o");
        texto = texto.replace("û", "u");
        texto = texto.replace("Â", "A");
        texto = texto.replace("Ê", "E");
        texto = texto.replace("Î", "I");
        texto = texto.replace("Ô", "O");
        texto = texto.replace("Û", "U");
        texto = texto.replace("ã", "a");
        texto = texto.replace("õ", "o");
        texto = texto.replace("Ã", "A");
        texto = texto.replace("Õ", "O");
        texto = texto.replace("ç", "c");
        texto = texto.replace("Ç", "C");
        texto = texto.replace("ü", "u");
        texto = texto.replace("Ü", "U");
        texto = texto.replace("à", "a");
        texto = texto.replace("è", "e");
        texto = texto.replace("ì", "i");
        texto = texto.replace("ò", "o");
        texto = texto.replace("ù", "u");
        texto = texto.replace("À", "A");
        texto = texto.replace("È", "E");
        texto = texto.replace("Ì", "I");
        texto = texto.replace("Ò", "O");
        texto = texto.replace("Ù", "U");
        texto = texto.replace("&", "e");

        return texto;
    }

    public static String valorPorExtenso(Integer vlr) {

        if (vlr == null) {
            vlr = 0;
        }
        if (vlr == 0) {
            return ("zero");
        }

        long inteiro = (long) Math.abs(vlr); // parte inteira do valor
        //ouble resto = vlr - inteiro;       // parte fracionária do valor

        String vlrS = String.valueOf(inteiro);
        if (vlrS.length() > 15) {
            return ("Erro: valor superior a 999 trilhões.");
        }

        String s = "", saux, vlrP;
        //String centavos = String.valueOf((int)Math.round(resto * 100));

        String[] unidade = {"", "um", "dois", "três", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez", "onze",
            "doze", "treze", "quatorze", "quinze", "dezesseis",
            "dezessete", "dezoito", "dezenove"};
        String[] centena = {"", "cento", "duzentos", "trezentos",
            "quatrocentos", "quinhentos", "seiscentos",
            "setecentos", "oitocentos", "novecentos"};
        String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
            "sessenta", "setenta", "oitenta", "noventa"};
        String[] qualificaS = {"", "mil", "milhão", "bilhão", "trilhão"};
        String[] qualificaP = {"", "mil", "milhões", "bilhões", "trilhões"};

        // definindo o extenso da parte inteira do valor
        int n, unid, dez, cent, tam, i = 0;
        boolean umReal = false, tem = false;
        while (!vlrS.equals("0")) {
            tam = vlrS.length();
            // retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
            // 1a. parte = 789 (centena)
            // 2a. parte = 456 (mil)
            // 3a. parte = 123 (milhões)
            if (tam > 3) {
                vlrP = vlrS.substring(tam - 3, tam);
                vlrS = vlrS.substring(0, tam - 3);
            } else { // última parte do valor
                vlrP = vlrS;
                vlrS = "0";
            }
            if (!vlrP.equals("000")) {
                saux = "";
                if (vlrP.equals("100")) {
                    saux = "cem";
                } else {
                    n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
                    cent = n / 100;                  // cent = 3 (centena trezentos)
                    dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
                    unid = (n % 100) % 10;           // unid = 1 (unidade um)
                    if (cent != 0) {
                        saux = centena[cent];
                    }
                    if ((n % 100) <= 19) {
                        if (saux.length() != 0) {
                            saux = saux + " e " + unidade[n % 100];
                        } else {
                            saux = unidade[n % 100];
                        }
                    } else {
                        if (saux.length() != 0) {
                            saux = saux + " e " + dezena[dez];
                        } else {
                            saux = dezena[dez];
                        }
                        if (unid != 0) {
                            if (saux.length() != 0) {
                                saux = saux + " e " + unidade[unid];
                            } else {
                                saux = unidade[unid];
                            }
                        }
                    }
                }
                if (vlrP.equals("1") || vlrP.equals("001")) {
                    if (i == 0) // 1a. parte do valor (um real)
                    {
                        umReal = true;
                    } else {
                        saux = saux + " " + qualificaS[i];
                    }
                } else if (i != 0) {
                    saux = saux + " " + qualificaP[i];
                }
                if (s.length() != 0) {
                    s = saux + ", " + s;
                } else {
                    s = saux;
                }
            }
            if (((i == 0) || (i == 1)) && s.length() != 0) {
                tem = true; // tem centena ou mil no valor
            }
            i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
        }

        return (s);
    }

    public static String getValorTotalNF(String texto) {
        String retorno = "0.0";

        try {

            String s = texto;
            Pattern p = Pattern.compile("<vNF>(.*?)</vNF>");
            Matcher m = p.matcher(s);
            if (m.find()) {
                retorno = m.group(1);
            }

        } catch (Exception e) {
            retorno = "0";
        }

        return retorno;
    }

//	public static void main(String[] args) {
//		String xml  ="<cProd>1</cProd><cEAN/><xProd>SAC Gerencial</xProd><NCM>00</NCM><CFOP>5933</CFOP><uCom>UND</uCom><qCom>2</qCom><vUnCom>133.35</vUnCom><vProd>266.70</vProd><cEANTrib/><uTrib>UND</uTrib><qTrib>2</qTrib><vUnTrib>133.35</vUnTrib><indTot>0</indTot><vNF>613.41</vNF>";
//		  getValorTotalNFSomaItens (xml);
//	}
    public static BigDecimal getValorTotalNFSomaItens(String texto) {
        BigDecimal totalNota = new BigDecimal(0.0);

        for (int i = 0; i < texto.length(); i++) {
            String tagTotalNota = texto.substring(i, i + 5);

            /**
             * SAIR DO LAÇO *
             */
            if (tagTotalNota.equalsIgnoreCase("<vNF>")) {
                break;
            }

            /**
             * TAG PROCURADA - <vUnTrib> *
             */
            String tag = texto.substring(i, i + 7);
            String tagValor;

            //if (tag.equalsIgnoreCase("<vUnTrib>")) {
            if (tag.equalsIgnoreCase("<vProd>")) {
                tagValor = texto.substring(i + 7);
                tagValor = tagValor.substring(0, 15);
                tagValor = tagValor.replaceAll("[^0-9.]", "");
                totalNota = totalNota.add(new BigDecimal(tagValor));

            }

        }

        return totalNota;

    }

    public static String getValorImpostoNF(String texto) {
//		    String s = texto;  
//		    int begin = s.indexOf(tagInicial);  
//		    int end = s.lastIndexOf(tagFinal);  
//		    int tam = tagInicial.length();

        // return  s.substring(begin+tam, end);  
        String retorno = "";
        String s = texto;
        Pattern p = Pattern.compile("<vISS>(.*?)</vISS>");
        Matcher m = p.matcher(s);
        if (m.find()) {
            retorno = m.group(1);
        }

        return retorno;

    }

    public static String formataDataHoraProtocolo(String dataHora) {
        // PEGA SOH A HORA //	 
        String retorno = dataHora.substring(dataHora.indexOf("T") + 1,
                dataHora.length());  //2012-06-20T12:56:02	 
        return retorno;
    }

    public static String getHoraProtocolo(String dataHora) {
        // PEGA SOH A HORA //	 
        String retorno = dataHora.substring(dataHora.indexOf("T") + 1,
                dataHora.length());  //2012-06-20T12:56:02	 
        return retorno;
    }

    public static String formataCompetencia(String competencia) {
        if (competencia != null) {
            competencia = competencia.substring(0, 2) + "/" + competencia.substring(2);
        }

        return competencia;
    }

    public static String[] getListEmail(String email) {

        String str = email;
        String[] temp;

        /* delimiter */
        String delimiter = ";";
        /* given string will be split by the argument delimiter provided. */
        temp = str.split(delimiter);
        /* print substrings */

        return temp;
    }

    /* 
	@SuppressWarnings("unchecked")
	public static Boolean enviarListBoleto(List<NotaFiscal> listNota) throws BaseException, MessagingException{
		 
		 boolean retorno=false;
		 List<Boleto>listBoletoEnvio = new ArrayList();		 
		 List<NotaFiscal> listTempNota= new ArrayList();
		 
		 if(listNota==null){
			 return false;		 
		 }
		 
		 if(listNota!=null && listNota.size()== 0){			 
			 return false;
		 }
		 
		
		 String emailAtual= getEmail(listNota.get(0).getBoleto());
		 //agrupando boletos
		 for(NotaFiscal nota : listNota){
			   listTempNota.add(nota);
			 	Boleto boleto =nota.getBoleto();
			if(emailAtual.equalsIgnoreCase(getEmail(boleto))){
				listBoletoEnvio.add(boleto);
			}
			 
		 }
		 
		 
		 //exclui boletos enviados
		 for(NotaFiscal nota : listNota){
				Boleto boleto =nota.getBoleto();
				if(emailAtual.equalsIgnoreCase(getEmail(boleto))){
					listTempNota.remove(nota);
				}
				 
		}
		 
		 
		 if(listTempNota!=null && listTempNota.size() > 0){
		 //chamada Recursiva
		 enviarListBoleto(listTempNota);
		 }
         
		 
		 return retorno;
	 }
     */
    public static Integer dataDiferencaDias(Date dataInicio, Date dataFim) {
        GregorianCalendar startTime = new GregorianCalendar();
        GregorianCalendar endTime = new GregorianCalendar();

        GregorianCalendar curTime = new GregorianCalendar();
        GregorianCalendar baseTime = new GregorianCalendar();

        startTime.setTime(dataInicio);
        endTime.setTime(dataFim);

        int dif_multiplier = 1;

        // Verifica a ordem de inicio das datas    
        if (dataInicio.compareTo(dataFim) < 0) {
            baseTime.setTime(dataFim);
            curTime.setTime(dataInicio);
            dif_multiplier = 1;
        } else {
            baseTime.setTime(dataInicio);
            curTime.setTime(dataFim);
            dif_multiplier = -1;
        }
        int resultadoDeAno = 0;
        int resultadoDeMes = 0;
        int resultadoDedia = 0;
        // Para cada mes e ano, vai de mes em mes pegar o ultimo dia para import acumulando    
        // no total de dias. Ja leva em consideracao ano bissesto    
        while (curTime.get(GregorianCalendar.YEAR) < baseTime.get(GregorianCalendar.YEAR)
                || curTime.get(GregorianCalendar.MONTH) < baseTime.get(GregorianCalendar.MONTH)) {
            int max_day = curTime.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
            resultadoDeMes += max_day;
            curTime.add(GregorianCalendar.MONTH, 1);
        }
        // Marca que é um saldo negativo ou positivo    
        resultadoDeMes = resultadoDeMes * dif_multiplier;
        // Retirna a diferenca de dias do total dos meses    
        resultadoDedia += (endTime.get(GregorianCalendar.DAY_OF_MONTH) - startTime.get(GregorianCalendar.DAY_OF_MONTH));

        return resultadoDeAno + resultadoDeMes + resultadoDedia;
    }

    /**
     * GERADOR DE MD5 *
     */
    public static String getMD5(String value) throws Exception {
        String retorno = "";

        if (value == null) {
            return retorno;
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }

        retorno = sb.toString();
        return retorno.trim();
    }

    public static String completarCom(String caracter, Integer qtdeCaracteres, String base) {
        if (base == null) {
            return null;
        }

        if (qtdeCaracteres <= base.length()) {
            return base;
        }

        StringBuffer bf = new StringBuffer();
        for (int i = 1; i <= qtdeCaracteres - base.length(); i++) {
            bf.append(caracter);
        }
        return bf.append(base).toString();
    }

    public static String formateCbrRight(String caracter, Integer qtdeCaracteres, String base) {
        if (base == null) {
            return null;
        }

        if (qtdeCaracteres <= base.length()) {
            return base;
        }

        StringBuffer bf = new StringBuffer();
        for (int i = 1; i <= qtdeCaracteres - base.length(); i++) {
            bf.append(caracter);

            if (bf.length() == qtdeCaracteres) {
                break;
            }
        }

        bf.insert(0, base);

        return removeCaracteresEspeciais(bf.toString().toUpperCase());
    }

    public static String formateCbrLeft(String caracter, Integer qtdeCaracteres, String base) {
        if (base == null) {
            return null;
        }

        if (qtdeCaracteres <= base.length()) {
            return base;
        }

        StringBuffer bf = new StringBuffer();
        for (int i = 1; i <= qtdeCaracteres - base.length(); i++) {
            bf.append(caracter);
            if (bf.length() == qtdeCaracteres) {
                break;
            }
        }
        return removeCaracteresEspeciais(bf.append(base).toString().toUpperCase());
    }

    public static String limitaString(Integer qtdeCaracteres, String base) {
        if (base == null) {
            return null;
        }

        if (base.length() < qtdeCaracteres) {
            return base;
        }

        String retorno = base.substring(0, qtdeCaracteres);
        return removeCaracteresEspeciais(retorno);
    }

    public static String getPrefixoCep(String cep) {
        if (cep == null || cep.length() < 10) {
            return "";
        }

        return apenasNumeros(cep.substring(0, 6));
    }

    public static String getSufixoCep(String cep) {
        if (cep == null || cep.length() < 10) {
            return "";
        }

        return cep.substring(7, 10);
    }

    public static String apenasNumeros(String num) {
        if (num == null) {
            return "";
        }

        String numStr = num.toString();
        numStr = numStr.replace(",", "");
        numStr = numStr.replace(".", "");

        return numStr;
    }

    public static String removeCaracteresEspeciais(String texto) {
        String strSemAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD);
        strSemAcentos = strSemAcentos.replaceAll("[^\\p{ASCII}]", "");
        strSemAcentos = strSemAcentos.replace("*", " ");
        strSemAcentos = strSemAcentos.replace("?", " ");
        strSemAcentos = strSemAcentos.replace("/", " ");
        strSemAcentos = strSemAcentos.replace("\\", " ");
        strSemAcentos = strSemAcentos.replace("$", " ");
        strSemAcentos = strSemAcentos.replace("(", " ");
        strSemAcentos = strSemAcentos.replace(")", " ");
        strSemAcentos = strSemAcentos.replace("@", " ");
        strSemAcentos = strSemAcentos.replace("!", " ");
        strSemAcentos = strSemAcentos.replace("#", " ");
        strSemAcentos = strSemAcentos.replace("%", " ");
        strSemAcentos = strSemAcentos.replace("&", " ");
        //return stringToAnsi(strSemAcentos);
        return strSemAcentos;
    }

    public static String stringToAnsi(String texto) {

        String unicode = null;
        try {
            byte[] retorno = texto.getBytes("Cp1252");
            unicode = new String(retorno);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return unicode;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatado = dt.format(date);
        return formatado;
    }

    public static void main(String[] args) throws Exception {
        Date date = new Date();
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String teste = dt.format(date);
        System.err.println(teste);

    }

    public static EntityManagerFactory JpaEntityManagerFactory() {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        ExternalContext externalContext = facesContext.getExternalContext();

        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        HttpSession session = request.getSession();

        EntityManagerFactory emf = (EntityManagerFactory) session.getAttribute("entityManagerFactory");

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("GestaoTarefasPU");

            EntityManager entityManager = emf.createEntityManager();

            if (emf != null) {
                session.setAttribute("entityManager", entityManager);
                session.setAttribute("entityManagerFactory", emf);
            }

        }

        return (EntityManagerFactory) session.getAttribute("entityManagerFactory");
    }

}
