import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import implementaciones.GrafoMA;



public class Main {

    static class Nodo {
        int destino;
        int peso;

        Nodo(int destino, int peso) {
            this.destino = destino;
            this.peso = peso;
        }
    }

    static GrafoMA leerRutas() {
        GrafoMA grafo = new GrafoMA();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("rutas.txt"));
            // Saltar la primera línea
            reader.readLine();
            // Leer las líneas 2 a 157
            for (int i = 0; i < 156; i++) {
                String line = reader.readLine();
                if (line != null) {
                    if (line.contains("#")) {
                        line = line.substring(0, line.indexOf("#")).trim();
                    }
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        int origen = Integer.parseInt(parts[0]);
                        int destino = Integer.parseInt(parts[1]);
                        int peso = Integer.parseInt(parts[2]);
                        grafo.agregarArista(origen, destino, peso);
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grafo;
    }

    class Grafoma {
        private final Map<Integer, List<Nodo>> adyacencias = new HashMap<>();

        void agregarArista(int origen, int destino, int peso) {
            this.adyacencias.computeIfAbsent(origen, k -> new ArrayList<>()).add(new Nodo(destino, peso));
        }

        void imprimir() {
            for (Map.Entry<Integer, List<Nodo>> entry : adyacencias.entrySet()) {
                for (Nodo nodo : entry.getValue()) {
                    System.out.println("Origen: " + entry.getKey() + ", Destino: " + nodo.destino + ", Peso: " + nodo.peso);
                }
            }
        }
    }

    //-----------------------------------------------------------------------
    
    static class Resultado {
        int cl;
        int cd;
        int[] vpa;
        int[] CDP;
        int[] CFP;

        Resultado(int cl, int cd, int[] vpa, int[] CDP, int[] CFP) {
            this.cl = cl;
            this.cd = cd;
            this.vpa = vpa;
            this.CDP = CDP;
            this.CFP = CFP;
        }
    }
    
    public static Resultado leerLineas() {
        int cl = 0;
        int cd = 0;
        int[] vpa = new int[50];
        int[] CDP = new int[8];
        int[] CFP = new int[8];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientesycentros.txt"));
            String line = reader.readLine();
            if (line != null && line.contains("#")) {
                line = line.substring(0, line.indexOf("#")).trim();
            }
            cl = Integer.parseInt(line);
            line = reader.readLine();
            if (line != null && line.contains("#")) {
                line = line.substring(0, line.indexOf("#")).trim();
            }
            cd = Integer.parseInt(line);
             // Leer las líneas 3 a 10

             for (int i = 0; i < 8; i++) {
                line = reader.readLine();
                if (line != null) {
                    if (line.contains("#")) {
                        line = line.substring(0, line.indexOf("#")).trim();
                    }
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        int position = Integer.parseInt(parts[0]);
                        CDP[position] = Integer.parseInt(parts[1]);
                        CFP[position] = Integer.parseInt(parts[2]);
                    }
                }
            }
            // Leer las líneas 11 a 60
            for (int i = 0; i < 50; i++) {
                line = reader.readLine();
                if (line != null) {
                    if (line.contains("#")) {
                        line = line.substring(0, line.indexOf("#")).trim();
                    }
                    String[] parts = line.split(",");
                    int position = Integer.parseInt(parts[0]);
                    int value = Integer.parseInt(parts[1]);
                    vpa[position] = value;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Resultado(cl, cd, vpa, CDP, CFP);
    }
    
    /* public static minim (CFP[],int C[]){
        n1=CrearNodoRaiz(c,CFP);
        vivos=CrearColaPrioridad();
        vivos.agregar(n1,n1.cotainf);
        cota=n1.cotaSup;
        mejorSolucion=null;
        while(vivos!= 0){
            nodo=primero(vivos);
            vivos.sacar(nodo);
            hijos=generarHijos(nodo,c,CFP);
            for(int h =0;h<hijos;h++){
                if(NoPodar(h,cota)){
                    if(esSolucion(h)){
                        if(esMejorSolucion(h,mejorSolucion)){
                            mejorSolucion=h;
                            cota=actualizar(cota,h);
                    }else{
                        vivo.agregar(h,h.cotainf);
                        cota=actualizar(cota,h);
                    }
                    }
                }
            }
        }
        return mejorSolucion.estado;
    } */
    
    //-----------------------------------------------------------------------
    public static void main(String[] args) {
        Resultado resultado = leerLineas();
        GrafoMA grafo = leerRutas();
        grafo.imprimir();
        
        int cl = resultado.cl;
        int cd = resultado.cd;
        int[] vpa = resultado.vpa;
        int[] CDP = resultado.CDP;
        int[] CFP = resultado.CFP;
        int[][] D = new int[cd][cl];
        
        System.out.println("Resultado: cl = " + cl + ", cd = " + cd);
        for (int i = 0; i < vpa.length; i++) {
            System.out.println("Posición: " + i + ", Valor: " + vpa[i]);
        }
        for (int i = 0; i < CDP.length; i++) {
            System.out.println("CDP Posición: " + i + ", Valor: " + CDP[i]);
        }
        for (int i = 0; i < CFP.length; i++) {
            System.out.println("CFP Posición: " + i + ", Valor: " + CFP[i]);
        }
        

        

        for(int i=0; i<cl;i++){
            //R=Dijkstra(G,i);
            for(int j=0; j<cd;j++){
                D[j][i]=(D[j][i]*vpa[i])+(CDP[j]*vpa[i]);
            }

        }
        int[] CDconst= new int [100];
        //CDconst=minim(D,CFP);

        int[][] CICD = new int[cd][cl];
        for(int i=0;i<cl;i++){
            int mejorSol=99999;
            int Pos=-1;
            for(int j=0;j<cd;j++){
                if(CDconst[j]==1){
                    if (D[j][i]<mejorSol){
                        mejorSol=D[j][i];
                        CICD[j][i]=1;
                        if (Pos!=-1)
                            CICD[Pos][i]=0;
                        Pos=j;
                    }else{
                        CICD[j][i]=0;
                    }
                }else{
                    CICD[j][i]=0;
                }
            }
        }
    }
}