package com.example.kusitms_team3.MainPagePackage;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kusitms_team3.AnalyzeActivity;
import com.example.kusitms_team3.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainPage_Fragment2 extends Fragment {
    private ImageView imageView;
    private ImageButton imageButton;
    public static ArrayList nameList = new ArrayList(); // 사람들의 이름을 담은 list

    public static ArrayList nameSequenceList = new ArrayList(); // 말한 사람들의 이름을 순서대로 저장하는 곳
    public static ArrayList textSequenceList = new ArrayList();

    public static String beforeName;

    public static String tmpSentence;
    public static int tmpNumber;

    //================================================= 출력
    public static ArrayList makeRoomList = new ArrayList(); //방을 만든 사람을 표시해주는 list ex) [1,0,0,0]
    public static ArrayList countList = new ArrayList(); //총 말을 몇번 했는지
    public static ArrayList fileList = new ArrayList();
    public static ArrayList longTextList = new ArrayList();


    //================================================= 출력
    public static ArrayList pointList = new ArrayList();
    public static ArrayList percentList = new ArrayList();

    public static float sum = 0;
    //================================================= 출력

    public Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_main_page_2, container, false);

        imageButton = rootview.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = null;
                InputStream inputStream = getResources().openRawResource(R.raw.kaka);
                InputStreamReader inputreader = new InputStreamReader(inputStream);
                BufferedReader buffreader = new BufferedReader(inputreader);
                StringBuilder text = new StringBuilder();

                try {

                    String tmp1; // 참가자들의 이름들을 담는 문장

                    String line = "";
                    while (( line = buffreader.readLine()) != null) {
                        Log.d("log",line);



                        //==============================================================

                        if(line.contains("님을 초대하였습니다.")) {
                            tmp1 = line;
                            String name[] = tmp1.split("님");

                            for(int i =0; i<name.length;i++) {
                                name[i] = name[i].replace(", ", "");
                                name[i] = name[i].replace("이 ", "");
                                name[i] = name[i].replace("을 초대하였습니다.", "");
                            }



                            for(int i=0;i<name.length;i++) {
                                if(!nameList.contains(name[i])) {
                                    nameList.add(name[i]);
                                }
                            }
                            nameList.remove(nameList.size()-1);

                            for(int i=0;i<nameList.size(); i++) {
                                if ( i == 0 ) {
                                    makeRoomList.add(1);
                                } else {
                                    makeRoomList.add(0);
                                }
                                countList.add(0);
                                fileList.add(0);
                                longTextList.add(0);

                            }
                        }


                        //==============================================================


                        else if(line.contains("]")) {
                            tmp1 = line;
                            String count[] = tmp1.split("]");
                            count[0] = count[0].replace("[", ""); //안세훈

                            nameSequenceList.add(count[0]);
                            beforeName = count[0];
                            textSequenceList.add(line);

                            for(int i =0; i<nameList.size();i++) {
                                if (nameList.get(i).equals(count[0])) {
                                    int tmp2 = (int) countList.get(i);
                                    tmp2 ++;
                                    countList.set(i, tmp2);
                                }
                            }
                        } else {
                            nameSequenceList.add(beforeName + "장문");
                            textSequenceList.add(line);

                        }

                        //==============================================================

                        if(line.contains("톡게시판 '투표':") || line.contains("파일:")) {
                            if(line.contains("]")) {
                                tmp1 = line;
                                String count[] = tmp1.split("]");
                                count[0] = count[0].replace("[", ""); //안세훈


                                for(int i =0; i<nameList.size();i++) {
                                    if (nameList.get(i).equals(count[0])) {
                                        int tmp2 = (int) fileList.get(i);
                                        tmp2 ++;
                                        fileList.set(i, tmp2);
                                    }
                                }
                            }
                        }

                        //==============================================================



                        //==============================================================

                    }


                } catch (IOException e) {
                    Log.d("log","읽기 실패");
                }

                for(int i=0; i<nameSequenceList.size(); i++) {
                    if (((String) nameSequenceList.get(i)).contains("장문") && !((String) nameSequenceList.get(i)).contains("null"))  {
                        nameSequenceList.set(i-1, (String)nameSequenceList.get(i));

                    }
                }


                tmpSentence = "";
                for(int i=0; i<nameSequenceList.size(); i++) {
                    if (((String) nameSequenceList.get(i)).contains("장문") && !((String) nameSequenceList.get(i)).contains("null"))  {
                        for (int j=0;j<nameList.size();j++) {
                            if (((String) nameSequenceList.get(i)).contains((String)nameList.get(j))) {
                                tmpSentence = tmpSentence + ((String)textSequenceList.get(i));
                                tmpNumber = j;
                            }
                        }

                    }
                }

                if(tmpSentence.length() > 100) {
                    int tmp2 = (int) longTextList.get(tmpNumber);
                    tmp2 ++;
                    longTextList.set(tmpNumber, tmp2);
                }



                Log.d("TAG", makeRoomList.toString());
                Log.d("TAG", countList.toString());
                Log.d("TAG", fileList.toString());
                Log.d("TAG", longTextList.toString());


                for(int i=0;i<nameList.size();i++) {
                    float a = (int)makeRoomList.get(i);
                    float b = (int)countList.get(i) / listsum(countList);
                    float c = (int)fileList.get(i) / listsum(fileList);
                    float d = (int)longTextList.get(i) / listsum(longTextList);

                    float result = (float) (0.56 * b + 0.24 * c + 0.15 + 0.06 * a);

                    pointList.add(result);
                    sum += result;

                }

                System.out.println(sum);
                for(int i=0;i<pointList.size();i++) {
                    int activity = (int) (((float) pointList.get(i) / sum ) * 100);
                    percentList.add(activity);
                    // 출력값임!!!
                    Log.d("TAG", nameList.get(i) + " 출력값: " + activity + "%");

                    //===================================================================
                }


                intent = new Intent(getActivity(), AnalyzeActivity.class);
                intent.putExtra("namearray", nameList);
                intent.putExtra("percentarray", percentList);
                startActivity(intent);
            }
        });


        return rootview;
    }

    public static float listsum(ArrayList arraylist) {
        float sum = 0;
        for(int i=0; i<arraylist.size();i++) {
            sum = sum + (int )arraylist.get(i);
        }
        return sum;
    }


}