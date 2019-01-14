/*
 * Copyright (C) 2017-2018 Lightbend Inc. <http://www.lightbend.com>
 */

package akka.cluster.http.management

import akka.cluster.MemberStatus
import akka.management.cluster.scaladsl.ClusterMembershipCheckSettings
import org.scalatest.{ Matchers, WordSpec }

class ClusterMembershipCheckSettingsSpec extends WordSpec with Matchers {

  "Member status parsing" must {
    "be case insensitive" in {
      ClusterMembershipCheckSettings.memberStatus("WeaklyUp") shouldEqual MemberStatus.WeaklyUp
      ClusterMembershipCheckSettings.memberStatus("Weaklyup") shouldEqual MemberStatus.WeaklyUp
      ClusterMembershipCheckSettings.memberStatus("weaklyUp") shouldEqual MemberStatus.WeaklyUp
      ClusterMembershipCheckSettings.memberStatus("Up") shouldEqual MemberStatus.Up
      ClusterMembershipCheckSettings.memberStatus("Exiting") shouldEqual MemberStatus.Exiting
      ClusterMembershipCheckSettings.memberStatus("down") shouldEqual MemberStatus.Down
      ClusterMembershipCheckSettings.memberStatus("joininG") shouldEqual MemberStatus.Joining
      ClusterMembershipCheckSettings.memberStatus("leaving") shouldEqual MemberStatus.Leaving
      ClusterMembershipCheckSettings.memberStatus("removed") shouldEqual MemberStatus.Removed
    }

    "have a useful error message for invalid values" in {

      intercept[IllegalArgumentException] {
        ClusterMembershipCheckSettings.memberStatus("cats") shouldEqual MemberStatus.Removed
      }.getMessage shouldEqual "'cats' is not a valid MemberStatus. See reference.conf for valid values"
    }
  }

}