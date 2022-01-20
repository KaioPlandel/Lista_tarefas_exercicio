package com.example.sqlteste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlteste.helper.TarefaDAO;
import com.example.sqlteste.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private EditText addTareda;
    private Button buttonSalvarTarefa;
    private Tarefa tarefaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        addTareda = findViewById(R.id.addTareda);
        buttonSalvarTarefa = findViewById(R.id.salvarTarefa);

        //recuperar tarefa caso seja edição

        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //configurar caixa de texto
        if(tarefaAtual != null){
            addTareda.setText(tarefaAtual.getNomeTarefa());

        }


        buttonSalvarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());


                if(tarefaAtual != null){//edição
                    String textoTarefa = addTareda.getText().toString();
                    if(!textoTarefa.isEmpty()){
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(textoTarefa);
                        tarefa.setId(tarefaAtual.getId());

                        //atualizar no banco de dados
                        if(tarefaDAO.atualizar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao Atualizar Tarefa", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao Atualizar a Tarefa", Toast.LENGTH_SHORT).show();

                        }


                    }


                }else{//salvar
                    Tarefa tarefa = new Tarefa();
                    String textoTarefa = addTareda.getText().toString();
                    if(!textoTarefa.isEmpty()){
                        tarefa.setNomeTarefa(textoTarefa);
                        if(tarefaDAO.salvar(tarefa)){
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao Salvar Tarefa", Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao Salvar Tarefa", Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        Toast.makeText(getApplicationContext(), "Preencha o campo", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}