require "./features/steps/shared.rb"

class Spinach::Features::UpdateATodoItem < Spinach::FeatureSteps
  include Shared::Standard

  step "I make a valid PUT request to a TODO item" do
    @response = Requests.put("/todo/3", "task=an%20updated%20task", {
      "Content-Type": "application/x-www-form-urlencoded"
    })
  end

  step "I make a PUT request with an unsupported media type to a TODO item" do
    @response = Requests.put("/todo/3", "<task>an updated task</task>", {
      "Content-Type": "text/xml"
    })
  end

  step "I make a PUT request with invalid values to the a TODO item" do
    @response = Requests.put("/todo/3", "", {
      "Content-Type": "application/x-www-form-urlencoded"
    })
  end
end
