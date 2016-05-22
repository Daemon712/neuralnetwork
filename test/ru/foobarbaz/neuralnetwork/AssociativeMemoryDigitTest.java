package ru.foobarbaz.neuralnetwork;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.foobarbaz.neuralnetwork.perceptron.logic.DigitAssociateTeacher;
import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AssociativeMemoryDigitTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{ DigitAssociateTeacher.getDigit(0) },
                new Object[]{ DigitAssociateTeacher.getDigit(1) },
                new Object[]{ DigitAssociateTeacher.getDigit(2) },
                new Object[]{ DigitAssociateTeacher.getDigit(3) },
                new Object[]{ DigitAssociateTeacher.getDigit(4) },
                new Object[]{ DigitAssociateTeacher.getDigit(5) },
                new Object[]{ DigitAssociateTeacher.getDigit(6) },
                new Object[]{ DigitAssociateTeacher.getDigit(7) },
                new Object[]{ DigitAssociateTeacher.getDigit(8) },
                new Object[]{ DigitAssociateTeacher.getDigit(9) }
        );
    }
    private static Perceptron perceptron;

    @BeforeClass
    public static void setUp() throws Exception {
        perceptron = DigitAssociateTeacher.studyPerceptron(10000);
    }

    private double[] digit;

    public AssociativeMemoryDigitTest(double[] digit) {
        this.digit = digit;
    }

    @Test
    public void testProcess() throws Exception {
        double[] result = perceptron.process(digit);
        for (int i = 0; i < result.length; i++) {
            double error = Math.abs(digit[i] - result[i]);
            Assert.assertTrue("Error = " + error, error < 0.1);
        }
    }
}
