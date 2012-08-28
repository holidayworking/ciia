package com.alag.ci.weka.turotial;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class WEKATutorial {

    public static void main(String[] args) throws Exception {
        WEKATutorial wekaTut = new WEKATutorial();
        wekaTut.executeWekaTutorial();
    }

    private void executeWekaTutorial() throws Exception {
        FastVector allAttributes = createAttributes();
        Instances learningDataSet = createLearningDataSet(allAttributes);
        Classifier predictiveModel = learnPredictiveModel(learningDataSet);
        Evaluation evaluation = evaluatePredictiveModel(predictiveModel, learningDataSet);
        System.out.println(evaluation.toSummaryString());
        predictUnknownCases(learningDataSet, predictiveModel);
    }

    private FastVector createAttributes() {
        Attribute ageAttribute = new Attribute("age");
        FastVector genderAttributeValues = new FastVector(2);
        genderAttributeValues.addElement("male");
        genderAttributeValues.addElement("female");
        Attribute genderAttribute = new Attribute("gender", genderAttributeValues);
        Attribute numLoginAttribute = new Attribute("numLogins");
        FastVector allAttributes = new FastVector(3);
        allAttributes.addElement(ageAttribute);
        allAttributes.addElement(genderAttribute);
        allAttributes.addElement(numLoginAttribute);
        return allAttributes;
    }

    private Instances createLearningDataSet(FastVector allAttributes) {
        Instances trainingDataSet = new Instances("wekaTutorial", allAttributes, 4);
        trainingDataSet.setClassIndex(2);
        addInstance(trainingDataSet, 20., "male", 5);
        addInstance(trainingDataSet, 30., "female", 2);
        addInstance(trainingDataSet, 40., "male", 3);
        addInstance(trainingDataSet, 35., "female", 4);
        return trainingDataSet;
    }

    private void addInstance(Instances trainingDataSet, double age,
            String gender, int numLogins) {
        Instance instance = createInstance(trainingDataSet, age, gender, numLogins);
        trainingDataSet.add(instance);
    }

    private Instance createInstance(Instances associatedDataSet, double age,
            String gender, int numLogins) {
        Instance instance = new Instance(3);
        instance.setDataset(associatedDataSet);
        instance.setValue(0, age);
        instance.setValue(1, gender);
        instance.setValue(2, numLogins);
        return instance;
    }

    private Classifier learnPredictiveModel(Instances learningDataSet) throws Exception {
        Classifier classifier = getClassifier();
        classifier.buildClassifier(learningDataSet);
        return classifier;
    }

    private Classifier getClassifier() {
        RBFNetwork rbfLearner = new RBFNetwork();
        rbfLearner.setNumClusters(2);
        return rbfLearner;
    }

    private Evaluation evaluatePredictiveModel(Classifier classifier,
            Instances learningDataSet) throws Exception {
        Evaluation learningSetEvaluation = new Evaluation(learningDataSet);
        learningSetEvaluation.evaluateModel(classifier, learningDataSet);
        return learningSetEvaluation;
    }

    private void predictUnknownCases(Instances learningDataSet,
            Classifier predictiveModel) throws Exception {
        Instance testMaleInstance = createInstance(learningDataSet, 32., "male", 0);
        Instance testFemaleInstance = createInstance(learningDataSet, 32., "female", 0);
        double malePrediction = predictiveModel.classifyInstance(testMaleInstance);
        double femalePrediction = predictiveModel.classifyInstance(testFemaleInstance);
        System.out.println("Predicted number of logins [age=32]:");
        System.out.println("\tMale = " + malePrediction);
        System.out.println("\tFemale = " + femalePrediction);
    }
}