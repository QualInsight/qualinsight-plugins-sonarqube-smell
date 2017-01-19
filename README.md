# Code Smells plugin for SonarQube
The Code Smells plugin for [SonarQube](http://www.sonarqube.org/) allows developers to report issues usually not seen by SonarQube but which should be taken into consideration when evaluating a project's technical debt. 

[![Build Status](https://travis-ci.org/QualInsight/qualinsight-plugins-sonarqube-smell.svg?branch=master)](https://travis-ci.org/QualInsight/qualinsight-plugins-sonarqube-smell) [![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.qualinsight.plugins.sonarqube:qualinsight-plugins-sonarqube-smell)](https://sonarqube.com/dashboard/index/com.qualinsight.plugins.sonarqube:qualinsight-plugins-sonarqube-smell) [![Technical debt ratio](https://sonarqube.com/api/badges/measure?key=com.qualinsight.plugins.sonarqube:qualinsight-plugins-sonarqube-smell&metric=sqale_debt_ratio)](https://sonarqube.com/dashboard/index/com.qualinsight.plugins.sonarqube:qualinsight-plugins-sonarqube-smell) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.qualinsight.plugins.sonarqube/qualinsight-plugins-sonarqube-smell-api/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.qualinsight.plugins.sonarqube/qualinsight-plugins-sonarqube-smell-api) [![javadoc.io](https://javadoc-emblem.rhcloud.com/doc/com.qualinsight.plugins.sonarqube/qualinsight-plugins-sonarqube-smell-api/badge.svg)](http://www.javadoc.io/doc/com.qualinsight.plugins.sonarqube/qualinsight-plugins-sonarqube-smell-api)


## Rationale

The great thing about SonarQube is that it reports in an objective and non disputable way issues based on a predefined set of rules or checks that need to be activated. Depending on the rules SonarQube administrators activate, reported issues will differ and so will reported technical debt.

While this is a (really) great way to report coding issues, my experience showed that it is not perfect due to two main reasons. 

1. SonarQube only reports issues and technical debt for the rules that have been activated. In other words, deactivate all rules and you'll obtain a project with a "A" SQALE rating and zero issues. 

2. Even if you do a great (and honest) job in selecting the rules to be activated, SonarQube is "only" a tool that does what it is told to do: it applies those rules. If the rules are not precise enough or not smart enough you'll end up with a project that shines in SonarQube (i.e. great rating, no issues, high coverage, good documentation, nothing to worry about) but if you scratch the surface a bit and ask a human to review it, you may have another feedback, one that makes you discover hidden code smells.

Thom Holwerda [posted a great comic strip](http://www.osnews.com/story/19266/Smells_m) a few years ago stating that the only valid measurement of code quality is the WTF! count per minute. While being humoristic, this observation is really realistic. If you've heard a team member use this interjection in your open space or if you've yourself mumbled it, I'm pretty sure you understand what I'm talking about. This comic strip and the underlying question of how to improve code quality was mentioned in Robert C. Martin's ["Clean Code"](http://www.amazon.com/gp/product/0132350882) book. It was also discussed in this [interesting blog post](http://www.gridshore.nl/2008/03/29/how-Smells-improve-code-quality-awareness/). Here is a excerpt from this blog post

> A [Smell] is typically raised when a developer more or less accidentally opens a class file and sees something that just doesn’t seem right. It doesn’t have to be a formal review. Getting code quality up after a [Smell] has been raised is easy. Just let the developer who found the [Smell] fix the code in such a way, that the [Smell] doesn’t apply to that code any more. However, the developer causing the bad code will not know, and continue with his habits

This is what motivated the creation of the Code Smells plugin. It has three major objectives 

1. Give voice to developers in order to unhide hidden code smells that cannot be usually detected with SonarQube and share them with the development team
2. Let developers provide useful information such as an evaluation of the remediation cost, the "Smell" category that has been detected, a message that will appear directly in SonarQube and aims at making those smells contribute to the overall technical debt reported by SonarQube
3. Let development teams access easily detected smells issues to be able to use this information during sprint plannings and code reviews.

Yes, we want to make smells visible and *force* team members to discuss them and fix them.

_Note 1:_ As you may have seen it, the word "WTF" has been replaced by "Smell" in the excerpt. I wish we could use the term "WTF" in order to refer to code smells. However it seems that many people are offended by such a language. Therefore, by respect for these people, I'll use the less controversial (but still valid) "Smell" word instead of WTF from now on.

## How does it work ?

The plugin is made of two parts:

1. A Java annotation named... `@Smell` that has a source retention that has to be used to report bad practices.

2. A SonarQube plugin that provides a single rule that analyzes both source code and test code and searches for `@Smell` annotations. 

## Usage

### Requirements and Compatibility Matrix

| Code Smell Plugin | SonarQube         | SonarQube [Java plugin](http://docs.sonarqube.org/display/PLUG/Java+Plugin) |
|-------------------|-------------------|-----------------------| 
| 2.0.1             | 4.5.x up to 5.1.x | 3.5 and above         |
| 3.0.0             | 5.2.x up to 5.5   | 3.7.1 up to 3.14      |
| 4.0.0             | 5.6.x and above   | 4.4 and above         |

_Note 2_: An internet connection on client's side is required to retrieve Google's visualisation API that is used to draw "Code Smells" widget's chart. No data is sent over the network.

### Plugin installation

The plugin can be directly installed from SonarQube's update center or [downloaded from GitHub releases](https://github.com/QualInsight/qualinsight-plugins-sonarqube-smell/releases).

After having placed the plugin's jar in `{SONARQUBE_INSTALL_DIRECTORY}/extensions/plugins` you need to restart your SonarQube instance.

You have then to add the `Code Smells` widget to your project or view dashboard.

The last installation step, if you want to have Smell annotations to contribute to the project's technical debt, is to add to your profile all rules part of the `Smells` rules repository.

That's it!

_Note 3:_ While I recommend activating all rules part of the `Smell` repository, you don't have to do it. If the rules are not activated, the plugin will still report Smell related technical debt in its own widget, but Smell annotations will not be counted as issues, and as such they will not contribute to the project's technical debt, nor impact the SQALE rating. Do this if you don't want to mix automated debt discovery with "subjective" debt declaration.  

_Note 4:_ If you decide to activate `Smell` rules in your profile, make sure to activate all of them, otherwise, only those that have been activated will appear as issues.

### Adding Smells to your code

Next step is to add `@Smell` annotations to your code. This section explains how to do it.

#### Maven dependency addition

In order to be able to use the `@Smell` annotation, the following dependency must be added to your pom.xml file :

```
<dependency>
    <groupId>com.qualinsight.plugins.sonarqube</groupId>
    <artifactId>qualinsight-plugins-sonarqube-smell-api</artifactId>
    <version>PLUGIN_VERSION</version>
</dependency>
```

_Note 5_: The dependency is available in [Maven central repository](https://maven-badges.herokuapp.com/maven-central/com.qualinsight.plugins.sonarqube/qualinsight-plugins-sonarqube-smell-api).

_Note 6:_ The API is packaged as an OSGi bundle.

#### Annotating code

The `@Smell` annotation can be placed on Java packages, types, methods, constructors, fields, parameters, local variables.

For instance :

```
@Smell(minutes=10,reason="This class should be redesigned in order to ...", type=SmellType.BAD_DESIGN)
public class MyClass {
    ...
}
```

It takes three mandatory parameters :

| Parameter | Type      | Mandatory   | Example                                           |
|-----------|-----------|-------------|---------------------------------------------------|
| minutes   | int       | yes         | 10                                                |
| reason    | String    | yes         | This class should be redesigned in order to...    |
| type      | SmellType | yes         | SmellType.BAD_DESIGN                              |

Multiple Smells can be declared using the `@Smells` annotation as follows:

```
@Smells({
    @Smell(minutes=20,reason="This class should be redesigned in order to ...", type=SmellType.BAD_DESIGN),
    @Smell(minutes=5,reason="This class should be renamed in order to highlight its responsibility", type=SmellType.UNCOMMUNICATIVE_NAME)
})
public class MyClass {
    ...
}
```

For more information, please check the [API's Javadoc](http://www.javadoc.io/doc/com.qualinsight.plugins.sonarqube/qualinsight-plugins-sonarqube-smell-api/).

#### Available Smell Types

The SmellType enum currently can take the following values (greatly inspired by Coding Horrors' [code smells](http://blog.codinghorror.com/code-smells/) and Industrial Logic's [smells to refactorings](http://www.industriallogic.com/wp-content/uploads/2005/09/smellstorefactorings.pdf)) :

| SmellType                  | When should I use it ?                                                     | Default severity |
|----------------------------|----------------------------------------------------------------------------|------------------|
| ABBREVIATIONS_USAGE        | Confusing abbreviations are being used instead of explicit names           | MAJOR            |
| ANTI_PATTERN               | An anti-pattern has been used.                                             | BLOCKER          |
| BAD_DESIGN                 | The design is really bad and should be improved.                           | BLOCKER          |
| BAD_FRAMEWORK_USAGE        | A framework is not used the way it should                                  | BLOCKER          |
| BAD_LOGGING                | The logging message, level, is inappropriate or the log is redundant.      | MAJOR            |
| HOW_COMMENT                | The comment or documentation focuses on the "how" instead of the "what"    | MAJOR            |
| INDECENT_EXPOSURE          | The class unnecessarily exposes its internals.                             | CRITICAL         |
| MEANINGLESS_COMMENT        | The comment or documentation text is meaningless.                          | MAJOR            |
| MIDDLE_MAN                 | The class delegates all its work, is it really needed ?                    | CRITICAL         |
| MISSING_DOCUMENTATION      | Mandatory documentation is missing.                                        | CRITICAL         |
| MISSING_IMPLEMENTATION     | A method's implementation is missing.                                      | MINOR            |
| MISSING_TEST               | An important or critical test is missing.                                  | BLOCKER          |
| MULTIPLE_RESPONSIBILITIES  | The class or method has multiple responsibilities.                         | BLOCKER          |
| NON_EXCEPTION              | The exceptions mechanism is used for non exceptional cases.                | MAJOR            |
| NON_COMPLIANCE_WITH_STANDARDS | The code does not comply with team or company development standards.    | CRITICAL         |
| ODDBALL_SOLUTION           | The problem is solved in multiple ways throughout the system.              | CRITICAL         |
| OVERCOMPLICATED_ALGORITHM  | There is a way to simplify an algorithm.                                   | MAJOR            |
| OTHER                      | Other smell that could not be categorized in any of the existing code smells types. | MINOR   |
| PRIMITIVES_OBSESSION       | The code relies too much on primitives instead of classes.                 | CRITICAL         |
| REFUSED_BEQUEST            | The class extends another class but does not use its methods.              | CRITICAL         |
| REINVENTED_WHEEL           | A library does the same job, probably better                               | BLOCKER          |
| SOLUTION_SPRAWL            | It takes too many classes to do anything useful.                           | CRITICAL         |
| SPECULATIVE_GENERALITY     | The code is written thinking about tomorrow's problems.                    | CRITICAL         |
| UNCOMMUNICATIVE_NAME       | The name does not communicate the purpose of the class, field, method.     | CRITICAL         |
| USELESS_TEST               | The test is useless (it tests nothing)                                     | MAJOR            |
| WRONG_LANGUAGE             | Wrong language (french, english, german...) is being used                  | MAJOR            |
| WRONG_LOGIC                | Wrong (business) logic is being used.                                      | BLOCKER          |

Feel free to ask for new values!

## Example results

The first screenshot depicts the Code Smells widget in action. It shows how many smells have been reported, their contribution to technical debt as well a distribution among possible Smell issue types. 

![Code Smells Measures Widget](smell_widget.png)

The second screenshot shows that Smell issues are added to regular SonarQube issues count. In this example the only activated rule is the Smell one. Therefore, the technical debt is equal to the one reported on previous screenshot. If we hadn't activated the rule (see _installation_ section) the plugin wouldn't had contributed to the debt.

![Code Smells in Issues Widget](smell_issues.png)

## Contributing to the project

If you want to contribute to this project, please have a look at the [developer toolset page](https://github.com/QualInsight/qualinsight-developer-toolset). It provides contribution guidelines I ask you to follow. Thanks in advance for your help !

## Conclusion

I hope you'll enjoy this small plugin as much as I enjoyed writing it ! Do not hesitate to request new Code Smells types and send comments as well as requests for improvement. 

A [Google group](https://groups.google.com/forum/#!forum/code-smells) named [Code Smells](https://groups.google.com/forum/#!forum/code-smells) has been created in order to facilitate discussions about this plugin.

Cheers !

