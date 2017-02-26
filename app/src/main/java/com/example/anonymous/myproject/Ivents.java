package com.example.anonymous.myproject;

public class Ivents {

    public static void main(String[] args) {
        int map[][]= new int[32][32];
        int ivents[]=new int[5];
        int k=0,p=0, count=0,count1=1;
        for (int i = 0; i < 5; i++) {
            ivents[i]=i+1;
        }
        for (int i = 0; i < 32; i++) {
            map[i][0]=-1;
            map[i][31]=-1;
            map[31][i]=-1;
            map[0][i]=-1;
        }
        for (int i = 0; i < 32; i++) {
            map[i][0]=-1;
            map[i][31]=-1;
            map[31][i]=-1;
            map[0][i]=-1;
        }
        for (int i = 1; i <31 ; i++) {
            for (int j = 1; j <i+1 ; j++) {
                map[i][j]=1;
                map[j][i]=2;

            }
        }
        for (int i = 1; i <31 ; i++) {
        for (int j = 1; j <i+1 ; j++) {
            map[31-i][j]=3;
            map[j][31-i]=4;
        }
    }
        while (count1>0) {
            for (int i = 1; i < 31; i++) {
                for (int j = 1; j < 31; j++) {
                    if ((int) (Math.random() * 60) == 50) {
                        count1 = 0;
                        while (k == 0) {
                            for (int l = 0; l < 5; l++) {
                                if (ivents[l] != 0) {
                                    count1++;
                                }
                            }
                            if (count1 == 0) {
                                break;
                            }
                            if (count != 0)
                                k = p;
                            else
                                k = (int) (Math.random() * 5 + 5);
                            if (ivents[k - 5] != 0) {
                                if (map[i][j]<5) {
                                    map[i][j] = k;
                                    ivents[k - 5]--;
                                }
                            } else {
                                k = 0;
                                count++;
                                if (k == 4)
                                    p = 0;
                                else
                                    p = k++;
                            }
                        }
                        k = 0;
                        count = 0;
                    }
                }
                if (count1 == 0)
                    break;
            }
            for (int l = 0; l < 5; l++) {
                if (ivents[l] != 0) {
                    count1++;
                }
            }
        }


        for (int i = 1; i < 31; i++) {
            for (int j = 1; j < 31; j++) {
                if ((int) (Math.random() * 60) == 50) {
                    count1=0;
                    while (k == 0) {
                        for (int l = 0; l < 5; l++) {
                            if (ivents[l]!=0){
                            count1++;
                            }
                        }
                        if(count1==0){
                            break;
                        }
                        if(count!=0)
                            k=p;
                        else
                        k = (int) (Math.random() * 5 + 5);
                        if (ivents[k - 5] != 0) {
                            map[i][j] = k;
                            ivents[k-5]--;
                        }
                        else {
                            k = 0;
                            count++;
                            if(k==4)
                            p=0;
                            else
                                p=k++;
                        }
                    }
                    k = 0;
                    count=0;
                }
            }
            if(count1==0)
                break;
        }
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 32; j++){
                System.out.printf("%3d",map[i][j]);
            }
            System.out.println();
        }
    }
}
