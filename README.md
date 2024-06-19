제가 개발한 프로젝트의 명칭은 ‘프라이버시 알람 앱’입니다.
이 앱을 만들게 된 이유는 소프트웨어의 발전으로 구글을 비롯하여 스마트폰 제조사에서 제공하는 다양한 알람 앱의 경우 데이터가 각 기계에 공유되거나 스마트폰 기계에 저장되어 개인의 일상생활 패턴이 추후에 노출되어 피해를 볼 수 있다고 생각이 되어서, 개인의 프라이버시를 존중하고 지킬 수 있는 나만의 알람 앱을 별도로 설치하여 사용할 수 있었으면 하는 바람으로 프라이버시 알람 앱을 만들게 되었습니다.

‘프라이버시 알람 앱’에 대해 설명해보겠습니다.
우측 하단에 있는 알람 설정 아이콘을 누르면 알람을 설정할 수 있는 Add Alarm 타임 피커가 화면에 띄워집니다. 알람 시간은 1일 24시간 형식으로 오전과 오후를 구분하지 않고 시간을 설정하는 방식으로 시간설정 후에 알람의 이름을 별도로 설정하고 싶을 경우에는 Alarm Text부분에 알람 이름을 작성한 후에 Add 버튼을 누르면, 알람 시간, 알람 이름으로 설정이 되어 “알람이 설정되었습니다!”라는 텍스트 메시지가 화면에 나타납니다. 또한 설정하고 싶은 알람시간들은 계속 추가할 경우에 설정된 알람들이 순서대로 나열되며, 해당 알람의 일정이 끝나 알람을 삭제하고 싶을 경우, 설정된 알람의 각 항목 우측에 있는 Delete 버튼을 누르면 해당 알람이 삭제됩니다.

저는 영상에서 설명하고 구현한 기능 외에 알람 소리 설정 기능을 별도로 구현하려고 사용자가 입력한 시간과 알람 내용을 받아와서 알람을 등록하고, 앱이 백그라운드에서도 동작하도록 만들기 위해 AlarmReceiver.java를 새로 작성하고 MainActivity.java에 코드를 추가하였으나 그 기능을 최종적으로 구현하진 못해 아쉬움이 남아 추후에 본 기능 외에도 추가적으로 다양한 시도와 구현을 해보려합니다.

유튜브 영상 (앱 소개 동영상)
https://youtu.be/y_7wSBpllzk
