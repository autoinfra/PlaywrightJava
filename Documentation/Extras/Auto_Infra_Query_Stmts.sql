BEGIN TRANSACTION;   

COMMIT TRANSACTION;  

USE [Auto_Infra]
GO
SELECT * FROM [dbo].[Auto_Infra_Main_Table]
GO

/**************** INSERT INTO TABLE*/


USE [Auto_Infra]
GO
INSERT INTO [dbo].[Auto_Infra_Main_Table]
           ([PK],[USER_NAME],[MACHINE_NAME],[FLOW_NAME],[SUITE_NAME],[CLASS_NAME],[METHOD_NAME],[METHOD_STATUS],[CLASS_STATUS],[SUITE_STATUS],[FLOW_STATUS],[START_TIME],[END_TIME],[EXECUTION_DATE])
     VALUES
           ('TestPK10',SYSTEM_USER,SYSTEM_USER,'Alexa Test','DAILY_SANITY','ALEXA PURCHASE','ADD TO CART','PASS','PASS','PASS','FAIL',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,convert(varchar, getdate(), 106))
GO 

/******************************************/




USE [Auto_Infra]
GO

/****** Object:  Table [dbo].[Auto_Infra_Main]    Script Date: 3/27/2020 10:29:54 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Auto_Infra_Main_Table](
	[PK] [nchar](10) NOT NULL,
	[USER_NAME] [varchar](50) NOT NULL,
	[MACHINE_NAME] [varchar](50) NOT NULL,
	[FLOW_NAME] [varchar](50) NULL,
	[SUITE_NAME] [varchar](50) NULL,
	[CLASS_NAME] [varchar](50) NULL,
	[METHOD_NAME] [varchar](50) NULL,
	[METHOD_STATUS] [varchar](50) NULL,
	[CLASS_STATUS] [varchar](50) NULL,
	[SUITE_STATUS] [varchar](50) NULL,
	[FLOW_STATUS] [varchar](50) NULL,
	[START_TIME] [time](7) NOT NULL,
	[END_TIME] [time](7) NOT NULL,
 CONSTRAINT [PK_Auto_Infra_Main] PRIMARY KEY CLUSTERED 
(
	[PK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO 

*/




