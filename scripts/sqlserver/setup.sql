IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'JavaSample')
BEGIN
    CREATE DATABASE JavaSample;
END
GO

IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'JavaSampleTest')
BEGIN
    CREATE DATABASE JavaSampleTest;
END
GO