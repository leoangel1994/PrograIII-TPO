import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import implementaciones.GrafoLA;

public class Main {
     public static int[] leerLineas() {
        int cl = 0;
        int cd = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("clientesycentros.txt"));
            String line = reader.readLine();
            if (line.contains("#")) {
                line = line.substring(0, line.indexOf("#")).trim();
            }
            cl = Integer.parseInt(line);
            line = reader.readLine();
            if (line.contains("#")) {
                line = line.substring(0, line.indexOf("#")).trim();
            }
            cd = Integer.parseInt(line);
            System.out.println("cl: " + cl);
            System.out.println("cd: " + cd);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new int[]{cl, cd};
    }

    public static void main(String[] args) {
        int[] resultados = leerLineas();
        int cl = resultados[0];
        int cd = resultados[1];
        int[] Vpa = new int[100];
        int[] CDP = new int[100];
        int[] CFP = new int[100];
        int[][] D = new int[cd][cl];

        System.out.println("Resultado: cl = " + cl + ", cd = " + cd);

        for(int i=0; i<cl;i++){
            //R=Dijkstra(G,i);
            for(int j=0; j<cd;j++){
                D[j][i]=(D[j][i]*Vpa[i])+(CDP[j]*Vpa[i]);
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