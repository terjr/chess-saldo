/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.saldo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.test.ActivityTestCase;

import com.chess.saldo.service.entities.Saldo;
import com.chess.saldo.test.R;

/**
 *
 * @author Bjorncs
 */
public class SaldoParserTest extends ActivityTestCase {

    public void testParsingMinSide() throws IOException, ServiceException {
        loadHtmlFile(R.raw.test1);
        loadHtmlFile(R.raw.test2);
        loadHtmlFile(R.raw.test3);
        loadHtmlFile(R.raw.test4);
    }

    private void loadHtmlFile(int rid) throws IOException, ServiceException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getInstrumentation().getContext().getResources().openRawResource(rid)));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        reader.close();
        String text = builder.toString();
        Saldo saldo = SaldoParser.parsePage(text);
        checkSaldoInformation(saldo);
    }

    private void checkSaldoInformation(Saldo saldo) {
        assertTrue(saldo.moneyUsed == -1 || (saldo.moneyUsed >= 0 && saldo.moneyUsed <= 1000));
        assertNotNull(saldo.strMoneyUsed);
        assertTrue(saldo.dataLeft == -1 || (saldo.dataLeft >= 0 && saldo.dataLeft <= 1000));
        assertTrue(saldo.dataTotal == -1 || (saldo.dataTotal > 0 && saldo.dataTotal <= 1000));
        assertTrue(saldo.minutesTotal == -1 || (saldo.minutesTotal >= 0 && saldo.minutesTotal <= 1000));
        assertTrue(saldo.minutesLeft == -1 || (saldo.minutesLeft > 0 && saldo.minutesLeft <= 1000));
        assertTrue(saldo.mmsLeft == -1 || (saldo.mmsLeft >= 0 && saldo.mmsLeft <= 1000));
        assertTrue(saldo.mmsTotal == -1 || (saldo.mmsTotal > 0 && saldo.mmsTotal <= 1000));
        assertTrue(saldo.smsLeft == -1 || (saldo.mmsLeft >= 0 && saldo.mmsLeft <= 1000));
        assertTrue(saldo.smsTotal == -1 || (saldo.smsTotal > 0 && saldo.smsTotal <= 1000));
    }
}