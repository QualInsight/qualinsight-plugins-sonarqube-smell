# WTF! SonarQube plugin
The QualInsight "What the fuck!" (WTF!) plugin for SonarQube is a simple plugin that allows developers to report, in a humoristic way, issues not seen by SonarQube but which should be taken into account when evaluating technical debt.

## Rationale

The great thing about SonarQube is that it reports in an objective, non disputable, way issues based on a predefined set of rules or checks that need to be activated. Depending on the rules SonarQube administrators activate reported issues will differ and so will technical debt.

While this (really) is great, my experience showed that it is not perfect due to two reasons. 

On the one hand, SonarQube only reports issues and technical debt for the rules that have been activated (in other words, deactivate all rules and you'll obtain a project with a "A" SQALE rating and zero issues.)

On the other hand, even if you do a great job in selecting the rules to be activated, SonarQube is "only" a tool that applies those rules. If they are not precise enough or not smart enough you'll end up with a project that shines in SonarQube (i.e. great rating, no issues, high coverage, documentation) but if you ask a human to review it, you may here something like "WTF!".

Thom Holwerda [posted a great comic strip](http://www.osnews.com/story/19266/WTFs_m) a few years ago about the WTF per minute metric, topic that was discussed in this [interesting blog post](http://www.gridshore.nl/2008/03/29/how-wtfs-improve-code-quality-awareness/). Here is a quote of this blog post:

> A WTF is typically raised when a developer more or less accidentally opens a class file and sees something that just doesn’t seem right. It doesn’t have to be a formal review.

> Getting code quality up after a WTF has been raised is easy. Just let the developer who found the WTF fix the code in such a way, that the WTF doesn’t apply to that code any more. However, the developer causing the bad code will not know, and continue with his habits.

This is where this plugin jumps in. It has three major objectives :

1. Give voice to developers to unhide hidden bad practices that cannot be usually detected with SonarQube

2. As correcting a "WTF!" may not be easy, and can be time consuming, let developers provide useful information such as an evaluation of the remediation cost, "WTF!" practice that has been detected and a message, that will appear directly in SonarQube and will contribute to the reported technical debt

3. Let development teams access easily detected "WTF!" issues to be able to use them during sprint plannings and code reviews.

## How does it work ?

The plugin is made of two parts:

1. A SonarQube plugin that provides a single rule that is able to analyze both source code and test code. 

2. A Java annotation named... @WTF that has a source retention that will be detected by the plugin.


## Usage

### Requirements

SonarQube's Java plugin version 3.5 must be installed. 

### Plugin installation

To be written

### Adding WTF! to your code

#### Maven dependency addition

The followinf dependency must be added to your code :

```
<dependency>
    <groupId>com.qualinsight.plugins.sonarqube</groupId>
    <artifactId>qualinsight-plugins-sonarqube-wtf-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

#### Annotating code

The @WTF annotation can be placed on Java packages, types, methods, constructors, fields, parameters, local variables.

For instance :

```
@WTF(minutes=10,reason="This class should be redesigned in order to ...", type=WTFTyp.BAD_DESIGN)
public class MyClass {
   ...
}
```

It takes three mandatory parameters :

| Parameter | Type     | Mandatory ? | Example                                           |
------------------------------------------------------------------------------------------
| minutes   | int      | yes         | 10                                                |
| reason    | String   | yes         | "This class should be redesigned in order to ..." |
| type      | WTFType  | yes         | WTFType.BAD_DESIGN                                |

#### Available WTF Types

The WTFType enum currently can take the following values :

| WTFType                    |
------------------------------
| ANTI_PATTERN               |
| BAD_DESIGN                 |
| OVERCOMPLICATED_ALGORTÎTHM |
| USELESS_TEST               |
| WRONG_LOGIC                |

## Example results

The first screenshot depicts the WTF! widget in action. It shows how many WTF! have been reported, their contribution to technical debt as well a distribution among possible WTF! issue types. 

![WTF! Measures Widget](wtf_widget.png)

The second screenshot shows that WTF! issues are added to regular SonarQube issues count. Note that in this case, as the WTF rule is the only one that has been activated the debt is equal to the one reported on previous screenshot.   

![WTF! Measures Widget](wtf_issues.png)

## Conclusion

I hope you'll enjoy this small plugin as much as I enjoyed writing it ! Do not hesitate to request new WTF types and send comments.

Cheers !
