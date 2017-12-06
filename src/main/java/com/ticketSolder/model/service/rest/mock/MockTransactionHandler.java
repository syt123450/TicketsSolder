package com.ticketSolder.model.service.rest.mock;

import com.ticketSolder.model.bean.transaction.*;
import com.ticketSolder.model.service.rest.TransactionHandler;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ss on 2017/11/29.
 */

@Service
@Profile("mock")
public class MockTransactionHandler implements TransactionHandler {

    @Override
    public TransactionAggregation searchTransaction(String userName, String password) {

        TransactionAggregation transactionAggregation = new TransactionAggregation();

        transactionAggregation.setResult(true);
        transactionAggregation.setReason("Failed to get transaction.");

        List<BasicTransactionInfo> basicTransactionInfoList = new ArrayList<>();



        BasicTransactionInfo basicTransactionInfo1 = new BasicTransactionInfo();
        basicTransactionInfo1.setUserName("syt");
        basicTransactionInfo1.setTransactionId(1);
        basicTransactionInfo1.setCanceled(false);
        basicTransactionInfo1.setRound(false);
        List<TransactionOutputSegmentInfo> transactionOutputSegmentInfoList1 = new ArrayList<>();
        TransactionOutputSegmentInfo transactionOutputSegmentInfo1 = new TransactionOutputSegmentInfo(
                "SB0600",
                true,
                "2017-11-30",
                "06:00",
                "2017-11-30",
                "06:05",
                'A',
                'B',
                1,
                1
        );
        transactionOutputSegmentInfoList1.add(transactionOutputSegmentInfo1);
        basicTransactionInfo1.setGoSegments(transactionOutputSegmentInfoList1);



        BasicTransactionInfo basicTransactionInfo2 = new BasicTransactionInfo();
        basicTransactionInfo2.setUserName("Yuntian Shen");
        basicTransactionInfo2.setTransactionId(2);
        basicTransactionInfo2.setCanceled(true);
        basicTransactionInfo2.setRound(false);
        List<TransactionOutputSegmentInfo> transactionOutputSegmentInfoList2 = new ArrayList<>();
        TransactionOutputSegmentInfo transactionOutputSegmentInfo2 = new TransactionOutputSegmentInfo(
                "SB0600",
                true,
                "2017-11-30",
                "06:00",
                "2017-11-30",
                "06:05",
                'A',
                'B',
                1,
                1
        );
        TransactionOutputSegmentInfo transactionOutputSegmentInfo3 = new TransactionOutputSegmentInfo(
                "SB0600",
                true,
                "2017-11-30",
                "06:08",
                "2017-11-30",
                "06:13",
                'B',
                'C',
                1,
                1
        );
        transactionOutputSegmentInfoList2.add(transactionOutputSegmentInfo2);
        transactionOutputSegmentInfoList2.add(transactionOutputSegmentInfo3);
        basicTransactionInfo2.setGoSegments(transactionOutputSegmentInfoList2);



        BasicTransactionInfo basicTransactionInfo3 = new BasicTransactionInfo();
        basicTransactionInfo3.setUserName("Shen Yuntian");
        basicTransactionInfo3.setTransactionId(3);
        basicTransactionInfo3.setCanceled(false);
        basicTransactionInfo3.setRound(true);
        List<TransactionOutputSegmentInfo> transactionOutputSegmentInfoList3 = new ArrayList<>();
        TransactionOutputSegmentInfo transactionOutputSegmentInfo4 = new TransactionOutputSegmentInfo(
                "SB0600",
                true,
                "2017-11-30",
                "06:00",
                "2017-11-30",
                "06:05",
                'A',
                'B',
                2,
                2
        );
        transactionOutputSegmentInfoList3.add(transactionOutputSegmentInfo4);

        List<TransactionOutputSegmentInfo> transactionOutputSegmentInfoList4 = new ArrayList<>();
        TransactionOutputSegmentInfo transactionOutputSegmentInfo5 = new TransactionOutputSegmentInfo(
                "NB1545",
                false,
                "2017-11-30",
                "17:57",
                "2017-11-30",
                "18:02",
                'B',
                'A',
                2,
                2
        );
        transactionOutputSegmentInfoList4.add(transactionOutputSegmentInfo5);

        basicTransactionInfo3.setGoSegments(transactionOutputSegmentInfoList3);
        basicTransactionInfo3.setBackSegments(transactionOutputSegmentInfoList4);

        basicTransactionInfoList.add(basicTransactionInfo1);
        basicTransactionInfoList.add(basicTransactionInfo2);
        basicTransactionInfoList.add(basicTransactionInfo3);

        transactionAggregation.setTransactions(basicTransactionInfoList);

        return transactionAggregation;
    }

    @Override
    public TransactionCreationResult createTransaction(CreateTransactionRequest createTransactionRequest) {

        TransactionCreationResult transactionCreationResult = new TransactionCreationResult();

        transactionCreationResult.setResult(true);
        transactionCreationResult.setReason("Failed to create transaction.");

        return transactionCreationResult;
    }

    @Override
    public TransactionDeletionResult deleteTransaction(DeleteTransactionRequest deleteTransactionRequest) {

        TransactionDeletionResult transactionDeletionResult = new TransactionDeletionResult();

        transactionDeletionResult.setResult(true);
        transactionDeletionResult.setReason("Failed to delete the transaction.");

        return transactionDeletionResult;
    }
}