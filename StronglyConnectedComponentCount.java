/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Vidya
 */
public class StronglyConnectedComponentCount {

     public static void stackByOrder(int v, Stack s, boolean[] visited, ArrayList<ArrayList<Integer>> verArray){
        
        visited[v]= true;
       
        for(int i = 0 ;i <verArray.get(v).size(); i++){
           if(!visited[verArray.get(v).get(i)]){
               stackByOrder(verArray.get(v).get(i),s,visited,verArray);
           }
         }
       s.push(v);
        
    }
    
    public static Graph getTranspose(Graph g){
        Graph gr = new Graph(g.ver);
        int v = gr.ver;
        for(int k = 0 ; k <v ; k ++){
        for(int i =0 ; i <g.vertexArray.get(k).size() ; i++){
            gr.vertexArray.get(g.vertexArray.get(k).get(i)).add(k);
        }
        }
        return gr;
    }
    
    public static void dfs(int v, boolean[] visisted,ArrayList<ArrayList<Integer>> vertexArray){
        visisted[v]= true;
      
        for(int i =0 ; i <vertexArray.get(v).size();i++){
            if(!visisted[vertexArray.get(v).get(i)]){
                dfs(vertexArray.get(v).get(i),visisted,vertexArray);
            }
        }
        
    }
    
    public static void sCC(Graph g,int count){
        
        
        int v= g.ver;
         ArrayList<ArrayList<Integer>> verArray = g.vertexArray;
        Stack s = new Stack();
        boolean[] visited = new boolean[v];
        for(int i = 0 ; i <v ; i++){
            visited[i]= false;
        }
        
        //Fill the stack by the order of the finishing times
        for(int i = 0; i < v; i++){
        if(!visited[i]){
             stackByOrder(i,s,visited,verArray);
        }
        }
        
         
         //Need to create reverse of graph - transpose
         
          Graph gr = getTranspose(g);
         
         //for each statck elemet we will do DFS again
          for(int i =0; i<v; i ++){
                  visited[i]= false;
              }
          while(!s.isEmpty()){
              
              int ver= s.pop();
              if(!visited[ver]){
              dfs(ver,visited,gr.vertexArray);
              count++;
              
              }
              
          }
           System.out.println(count);
         
        
    }
    
    public static void main(String args[]) throws FileNotFoundException{
        
      
         java.io.File file = new java.io.File(args[0]);
         
         try {
         Scanner scan = new Scanner(file);
        int ver =0;
       
         ArrayList<Integer> edgeArr = new ArrayList<>();
        
         while(scan.hasNextInt()){
             int v = scan.nextInt();
            // v = v-1;
              if(!edgeArr.contains(v)){
                ver ++;
             }
             edgeArr.add(v);
             
         }
         int min = Collections.min(edgeArr);
         if(ver == 0){
             System.out.println("Graph is Empty");
             return;
         }
         
         Graph g  = new  Graph(ver);
         int i =0;
         while(i<edgeArr.size()){
             g.addEdge(edgeArr.get(i)-min ,edgeArr.get(i+1)- min);
             i = i+2;
         }  
         int count = 0;
         
         
         
        sCC(g,count);
         }
 
     catch( FileNotFoundException e){
     System.out.println("File is not found " + e);
        }
    }
}
