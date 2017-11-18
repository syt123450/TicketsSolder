-- Table used to store schedule for each train
-- Time range:   6:00 ~ 21:00
-- Total 26 station:  from A to Z
-- Time duration between each station is 5 min, e.g. from A to B needs 5 min, from A to C needs 10 min
-- Each stop requires 3 min
-- Regular train start every 15 min both for A->Z and Z->A
-- Express train start every 60 min both for A->Z and Z->A
-- Trains A->Z named as SB<TimeDeparturer>; Trains Z->A named as NB<TimeDeparture>

CREATE TABLE TimeSchedule (
  id    int NOT NULL AUTO_INCREMENT,
  trainName  VARCHAR(20) NOT NULL,
  ifExpress   BOOLEAN DEFAULT 0,
  departA     TIME,
  departB     TIME,
  departC     TIME,
  departD     TIME,
  departE     TIME,
  departF     TIME NOT NULL,
  departG     TIME,
  departH     TIME,
  departI     TIME,
  departJ     TIME,
  departK     TIME NOT NULL,
  departL     TIME,
  departM     TIME,
  departN     TIME,
  departO     TIME,
  departP     TIME NOT NULL,
  departQ     TIME,
  departR     TIME,
  departS     TIME,
  departT     TIME,
  departU     TIME NOT NULL,
  departV     TIME,
  departW     TIME,
  departX     TIME,
  departY     TIME,
  departZ     TIME,

  arriveA     TIME,
  arriveB     TIME,
  arriveC     TIME,
  arriveD     TIME,
  arriveE     TIME,
  arriveF     TIME NOT NULL,
  arriveG     TIME,
  arriveH     TIME,
  arriveI     TIME,
  arriveJ     TIME,
  arriveK     TIME NOT NULL,
  arriveL     TIME,
  arriveM     TIME,
  arriveN     TIME,
  arriveO     TIME,
  arriveP     TIME NOT NULL,
  arriveQ     TIME,
  arriveR     TIME,
  arriveS     TIME,
  arriveT     TIME,
  arriveU     TIME NOT NULL,
  arriveV     TIME,
  arriveW     TIME,
  arriveX     TIME,
  arriveY     TIME,
  arriveZ     TIME,
  PRIMARY KEY (id)
);




-- Script used to insert time schedule for 06:00, 06:15, 06:30 and 06:45
SET @timeDiff = TIME_TO_SEC('00:08:00'), @timeDiffExp = TIME_TO_SEC('00:28:00'),
@baseTime_depart1 = TIME_TO_SEC('06:00:00'), @baseTime_depart2 = TIME_TO_SEC('06:15:00'), @baseTime_depart3 = TIME_TO_SEC('06:30:00'), @baseTime_depart4 = TIME_TO_SEC('06:45:00'),
@baseTime_arrive1 = TIME_TO_SEC('06:25:00'), @baseTime_arrive2 = TIME_TO_SEC('06:20:00'), @baseTime_arrive3 = TIME_TO_SEC('06:35:00'), @baseTime_arrive4 = TIME_TO_SEC('06:50:00');
-- SELECT (@baseTime_depart + 10*@timeDiff), SEC_TO_TIME(@baseTime_depart + 10*@timeDiff);

INSERT INTO TimeSchedule (
  trainName,
  departA,departB,departC,departD,departE,
  departF,departG,departH,departI,departJ,
  departK,departL,departM,departN,departO,
  departP,departQ,departR,departS,departT,
  departU,departV,departW,departX,departY,
  departZ,
  arriveA,arriveB,arriveC,arriveD,arriveE,
  arriveF,arriveG,arriveH,arriveI,arriveJ,
  arriveK,arriveL,arriveM,arriveN,arriveO,
  arriveP,arriveQ,arriveR,arriveS,arriveT,
  arriveU,arriveV,arriveW,arriveX,arriveY,
  arriveZ
)
VALUES
("SB0600",
  SEC_TO_TIME(@baseTime_depart1 + 0*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_depart1 + 1*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_depart1 + 2*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_depart1 + 3*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_depart1 + 4*@timeDiffExp), NULL, NULL, NULL, NULL,
  NULL,
  NULL, NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_arrive1 + 0*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_arrive1 + 1*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_arrive1 + 2*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_arrive1 + 3*@timeDiffExp), NULL, NULL, NULL, NULL,
  SEC_TO_TIME(@baseTime_arrive1 + 4*@timeDiffExp)
),
("SB0615",
  SEC_TO_TIME(@baseTime_depart2 + 0*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 1*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 2*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 3*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 4*@timeDiff),
  SEC_TO_TIME(@baseTime_depart2 + 5*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 6*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 7*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 8*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 9*@timeDiff),
  SEC_TO_TIME(@baseTime_depart2 + 10*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 11*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 12*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 13*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 14*@timeDiff),
  SEC_TO_TIME(@baseTime_depart2 + 15*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 16*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 17*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 18*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 19*@timeDiff),
  SEC_TO_TIME(@baseTime_depart2 + 20*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 21*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 22*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 23*@timeDiff), SEC_TO_TIME(@baseTime_depart2 + 24*@timeDiff),
  NULL,
  NULL, SEC_TO_TIME(@baseTime_arrive2 + 0*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 1*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 2*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 3*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive2 + 4*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 5*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 6*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 7*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 8*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive2 + 9*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 10*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 11*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 12*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 13*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive2 + 14*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 15*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 16*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 17*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 18*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive2 + 19*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 20*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 21*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 22*@timeDiff), SEC_TO_TIME(@baseTime_arrive2 + 23*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive2 + 24*@timeDiff)
  ),
("SB0630",
  SEC_TO_TIME(@baseTime_depart3 + 0*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 1*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 2*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 3*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 4*@timeDiff),
  SEC_TO_TIME(@baseTime_depart3 + 5*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 6*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 7*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 8*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 9*@timeDiff),
  SEC_TO_TIME(@baseTime_depart3 + 10*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 11*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 12*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 13*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 14*@timeDiff),
  SEC_TO_TIME(@baseTime_depart3 + 15*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 16*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 17*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 18*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 19*@timeDiff),
  SEC_TO_TIME(@baseTime_depart3 + 20*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 21*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 22*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 23*@timeDiff), SEC_TO_TIME(@baseTime_depart3 + 24*@timeDiff),
  NULL,
  NULL, SEC_TO_TIME(@baseTime_arrive3 + 0*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 1*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 2*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 3*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive3 + 4*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 5*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 6*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 7*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 8*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive3 + 9*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 10*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 11*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 12*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 13*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive3 + 14*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 15*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 16*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 17*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 18*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive3 + 19*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 20*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 21*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 22*@timeDiff), SEC_TO_TIME(@baseTime_arrive3 + 23*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive3 + 24*@timeDiff)
  ),
("SB0645",
  SEC_TO_TIME(@baseTime_depart4 + 0*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 1*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 2*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 3*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 4*@timeDiff),
  SEC_TO_TIME(@baseTime_depart4 + 5*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 6*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 7*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 8*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 9*@timeDiff),
  SEC_TO_TIME(@baseTime_depart4 + 10*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 11*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 12*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 13*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 14*@timeDiff),
  SEC_TO_TIME(@baseTime_depart4 + 15*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 16*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 17*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 18*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 19*@timeDiff),
  SEC_TO_TIME(@baseTime_depart4 + 20*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 21*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 22*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 23*@timeDiff), SEC_TO_TIME(@baseTime_depart4 + 24*@timeDiff),
  NULL,
  NULL, SEC_TO_TIME(@baseTime_arrive4 + 0*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 1*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 2*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 3*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive4 + 4*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 5*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 6*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 7*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 8*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive4 + 9*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 10*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 11*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 12*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 13*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive4 + 14*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 15*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 16*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 17*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 18*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive4 + 19*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 20*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 21*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 22*@timeDiff), SEC_TO_TIME(@baseTime_arrive4 + 23*@timeDiff),
  SEC_TO_TIME(@baseTime_arrive4 + 24*@timeDiff)
  )
;

UPDATE TimeSchedule SET ifExpress =TRUE WHERE id in (1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61);

-- ********************************************** --
-- Need to update for using id as the primary Key --
-- ********************************************** --
-- Table used to hold ticket amount for each segments between two stations
CREATE TABLE TicketAmount (
  trainName   VARCHAR(20) NOT NULL,
  segmentAB   INT NOT NULL DEFAULT 0,
  segmentBC   INT NOT NULL DEFAULT 0,
  segmentCD   INT NOT NULL DEFAULT 0,
  segmentDE   INT NOT NULL DEFAULT 0,
  segmentEF   INT NOT NULL DEFAULT 0,
  segmentFG   INT NOT NULL DEFAULT 0,
  segmentGH   INT NOT NULL DEFAULT 0,
  segmentHI   INT NOT NULL DEFAULT 0,
  segmentIJ   INT NOT NULL DEFAULT 0,
  segmentJK   INT NOT NULL DEFAULT 0,
  segmentKL   INT NOT NULL DEFAULT 0,
  segmentLM   INT NOT NULL DEFAULT 0,
  segmentMN   INT NOT NULL DEFAULT 0,
  segmentNO   INT NOT NULL DEFAULT 0,
  segmentOP   INT NOT NULL DEFAULT 0,
  segmentPQ   INT NOT NULL DEFAULT 0,
  segmentQR   INT NOT NULL DEFAULT 0,
  segmentRS   INT NOT NULL DEFAULT 0,
  segmentST   INT NOT NULL DEFAULT 0,
  segmentTU   INT NOT NULL DEFAULT 0,
  segmentUV   INT NOT NULL DEFAULT 0,
  segmentVW   INT NOT NULL DEFAULT 0,
  segmentWX   INT NOT NULL DEFAULT 0,
  segmentXY   INT NOT NULL DEFAULT 0,
  segmentYZ   INT NOT NULL DEFAULT 0,
  PRIMARY KEY (trainName)
);

INSERT INTO TicketAmount (trainName) VALUES
('SB0600')
;

UPDATE TicketAmount
SET
  segmentAB   = 1000,
  segmentBC   = 1000,
  segmentCD   = 1000,
  segmentDE   = 1000,
  segmentEF   = 1000,
  segmentFG   = 1000,
  segmentGH   = 1000,
  segmentHI   = 1000,
  segmentIJ   = 1000,
  segmentJK   = 1000,
  segmentKL   = 1000,
  segmentLM   = 1000,
  segmentMN   = 1000,
  segmentNO   = 1000,
  segmentOP   = 1000,
  segmentPQ   = 1000,
  segmentQR   = 1000,
  segmentRS   = 1000,
  segmentST   = 1000,
  segmentTU   = 1000,
  segmentUV   = 1000,
  segmentVW   = 1000,
  segmentWX   = 1000,
  segmentXY   = 1000,
  segmentYZ   = 1000
WHERE
  trainName in ('SB0600');