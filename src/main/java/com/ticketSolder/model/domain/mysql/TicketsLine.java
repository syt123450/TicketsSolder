package com.ticketSolder.model.domain.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Created by ss on 2017/11/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketsLine {

    private int trainID;
    private Date date;
    private int AB;
    private int BC;
    private int CD;
    private int DE;
    private int EF;
    private int FG;
    private int GH;
    private int HI;
    private int IJ;
    private int JK;
    private int KL;
    private int LM;
    private int MN;
    private int NO;
    private int OP;
    private int PQ;
    private int QR;
    private int RS;
    private int ST;
    private int TU;
    private int UV;
    private int VW;
    private int WX;
    private int XY;
    private int YZ;

    public TicketsLine(int trainID, Date date, int initNumber) {
        this.trainID = trainID;
        this.date = date;
        this.AB = initNumber;
        this.BC = initNumber;
        this.CD = initNumber;
        this.DE = initNumber;
        this.EF = initNumber;
        this.FG = initNumber;
        this.GH = initNumber;
        this.HI = initNumber;
        this.IJ = initNumber;
        this.JK = initNumber;
        this.KL = initNumber;
        this.LM = initNumber;
        this.MN = initNumber;
        this.NO = initNumber;
        this.OP = initNumber;
        this.PQ = initNumber;
        this.QR = initNumber;
        this.RS = initNumber;
        this.ST = initNumber;
        this.TU = initNumber;
        this.UV = initNumber;
        this.VW = initNumber;
        this.WX = initNumber;
        this.XY = initNumber;
        this.YZ = initNumber;
    }
}
