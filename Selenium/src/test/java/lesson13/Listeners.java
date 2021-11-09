package lesson13;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {


        @Override
        public void onStart(ITestContext arg0) {
            System.out.println("------------------starting Execution-------------------------");

        }

        @Override
        public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTestStart(ITestResult arg0) {
            System.out.println("------------------Starting Test: " + arg0.getName() + "-------------------------");
        }

        @Override
        public void onTestSuccess(ITestResult arg0) {
            System.out.println("------------------Test: " + arg0.getName() + "-------------------------");
        }

        @Override
        public void onTestFailure(ITestResult arg0) {
            System.out.println("------------------Test Failure: " + arg0.getName() + "-------------------------");
        }

        @Override
        public void onTestSkipped(ITestResult arg0) {
            System.out.println("------------------Starting Test: " + arg0.getName() + "-------------------------");

        }

        @Override
        public void onFinish(ITestContext arg0) {
            System.out.println("------------------ended Execution-------------------------");

        }


}
