package com.example.alu.p_58_dialogo_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import java.util.Vector;

/**
 * Created by alu on 02/02/2018.
 */

public class MiDialogo extends DialogFragment {
    String[] colores = { "BD", "XML", "PDM" };
    private Vector<String> items_selecc;
    @Override

        public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String tipo = getArguments().getString("dato");
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(tipo)
                    .setItems(colores, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            miEscuchador.onDialogPositiveClick(colores[which]);
                        }
                    });
            return builder.create();

        }
    public interface MiDialogoListener {
        void onDialogPositiveClick(String devuelto);
    }

    MiDialogoListener miEscuchador;

    // Sobreescribimos el método onAttach() para instanciar el
//escuchador
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            miEscuchador = (MiDialogoListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement MiDialogoListener");
        }
    }

    public static MiDialogo newInstance(String datoRecogido) {
        MiDialogo newInstance = new MiDialogo();
        Bundle args = new Bundle();
        args.putString("dato", datoRecogido);
        newInstance.setArguments(args);
        return newInstance;
    }
    public void onDetach () {
        super.onDetach();
        miEscuchador=null;
    }
}
